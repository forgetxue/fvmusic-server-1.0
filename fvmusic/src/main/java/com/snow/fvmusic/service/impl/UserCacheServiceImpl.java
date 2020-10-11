package com.snow.fvmusic.service.impl;

import com.snow.fvmusic.generator.entities.User;
import com.snow.fvmusic.service.RedisService;
import com.snow.fvmusic.service.UserCacheService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-21
 * @Description：
 * @Modified By：
 * @Version:
 */
@Service
public class UserCacheServiceImpl implements UserCacheService {
    @Resource
    private RedisService redisService;
    @Value("${redis.database}")
    private  String DATABASE;
    @Value("${redis.key}")
    private String KEY;
    @Value("${redis.expired.userExpired}")
    private long expired;
    private final String preKey = DATABASE + "." + KEY + ".";

    @Override
    public void setUser(String key, User user) {
        redisService.set(preKey + key, user, expired);
    }

    @Override
    public void setUser(User user) {
        String key = preKey + user.getId();
        redisService.set(key, user, expired);
    }

    @Override
    public void delUser(long uid) {
        redisService.del(preKey + uid);
    }

    @Override
    public void delUser(String key) {
        redisService.del(key);
    }

    @Override
    public User getUser(long uid) {
        return (User) redisService.get(preKey + uid);
    }

    @Override
    public User getUser(String key) {
        return (User) redisService.get(preKey + key);
    }
}
