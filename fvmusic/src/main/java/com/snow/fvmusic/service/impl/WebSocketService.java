package com.snow.fvmusic.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.sql.ast.AutoIncrementType;
import com.snow.fvmusic.bo.CommentAndGreatParam;
import com.snow.fvmusic.service.ArticleVOService;
import com.snow.fvmusic.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author ：snow
 * @Date ：Created in 2020-10-06
 * @Description：
 * @Modified By：
 * @Version:
 */
@Component
@ServerEndpoint(value = "/websocket/{userId}")
@Slf4j
public class WebSocketService {
    private static AtomicInteger onlineCount = new AtomicInteger(0);
    private static Map<Long, WebSocketService> clients = new ConcurrentHashMap<>();
    private Session session;
    private Long userId;
    @Value("${redis.message.comment}")
    private String MESSAGE_COMMENT_KEY_PRE;
    @Value("${redis.message.great}")
    private String MESSAGE_GREAT_KEY_PRE;
    @Resource
    private RedisService redisService;
    public static WebSocketService webSocketService;
    @PostConstruct
    public void init(){
        webSocketService = this;
        webSocketService.redisService = this.redisService;
        webSocketService.MESSAGE_COMMENT_KEY_PRE = this.MESSAGE_COMMENT_KEY_PRE;
        webSocketService.MESSAGE_GREAT_KEY_PRE = this.MESSAGE_GREAT_KEY_PRE;
    }
    @OnOpen
    public void onOpen(@PathParam("userId") Long userId, Session session) throws IOException {
        this.userId = userId;
        this.session = session;
        addOnlineCount();
        clients.put(userId, this);
        System.out.println("已连接" + getOnlineCount());
        // 检查redis是否有消息，有则发送
        List<Object> comments = webSocketService.redisService.lRange(webSocketService.MESSAGE_COMMENT_KEY_PRE + userId, 0, -1);
        List<Object> great = webSocketService.redisService.lRange(webSocketService.MESSAGE_GREAT_KEY_PRE + userId, 0, -1);
        if (!CollUtil.isEmpty(comments)){
            sendMessageTo(JSONUtil.toJsonStr(comments), userId);
//            webSocketService.redisService.del(webSocketService.MESSAGE_COMMENT_KEY_PRE + userId);
        }
        if (!CollUtil.isEmpty(great)){
            sendMessageTo(JSONUtil.toJsonStr(great), userId);
//            webSocketService.redisService.del(webSocketService.MESSAGE_GREAT_KEY_PRE + userId);
        }

    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(userId);
        subOnlineCount();
        System.out.println("已连接" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        // DataWrapper res = new DataWrapper();
        // System.out.println("message:" + message);
        // JSONObject req = JSONObject.parseObject(message);


        // 发送数据给服务端
        // sendMessageAll(JSON.toJSONString(res));
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, Long to) throws IOException {
        log.info("socket send message to =====>" + to);
        for (WebSocketService item : clients.values()) {
            if (item.userId.equals(to))
                item.session.getAsyncRemote().sendText(message);
        }

    }

    public void sendMessageAll(String message) throws IOException {
        for (WebSocketService item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static int getOnlineCount() {
        return onlineCount.intValue();
    }

    public static  void addOnlineCount() {
        WebSocketService.onlineCount.incrementAndGet();
    }

    public static  void subOnlineCount() {
        WebSocketService.onlineCount.decrementAndGet();
    }

    public static synchronized Map<Long, WebSocketService> getClients() {
        return clients;
    }

}
