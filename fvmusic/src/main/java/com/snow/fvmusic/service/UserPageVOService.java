package com.snow.fvmusic.service;

import com.snow.fvmusic.vo.UserPageArticlesAndPoolsVO;

import java.util.Map;

/**
 * @Author ：snow
 * @Date ：Created in 2020-10-05
 * @Description：
 * @Modified By：
 * @Version:
 */
public interface UserPageVOService {

    UserPageArticlesAndPoolsVO userPageArticlesAndPool(Long userId);

    /**
     * 登录用户获取点赞个评论的消息
     * @return
     */
    Map<String, Object> userMessage(Long userId);

}
