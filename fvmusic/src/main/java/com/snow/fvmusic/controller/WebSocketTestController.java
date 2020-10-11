package com.snow.fvmusic.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.snow.fvmusic.bo.CommentAndGreatParam;
import com.snow.fvmusic.service.impl.WebSocketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author ：snow
 * @Date ：Created in 2020-10-06
 * @Description：
 * @Modified By：
 * @Version:
 */
@RestController
@Api("webSocket测试")
public class WebSocketTestController {

    @Resource
    private  WebSocketService webSocketService;
    @GetMapping("/websocket/test/{userId}")
    @ApiOperation("websocket 测试接口")
    public String  test(@PathVariable Long  userId) {
        CommentAndGreatParam param = new CommentAndGreatParam();
        param.setArticleName("秦时明月");
        param.setUsername("小庄");
        param.setMessage("小庄赞了你的分享 “秦时明月“ ");
        try{
            webSocketService.sendMessageTo(JSONUtil.toJsonStr(param),userId);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "消息已发送";
    }
}
