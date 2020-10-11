package com.snow.fvmusic.service;

import com.snow.fvmusic.generator.entities.User;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-21
 * @Description： redis缓存用户信息
 * @Modified By：
 * @Version:
 */
public interface UserCacheService {

    /**
     * 缓存User
     * @param user
     */
    void setUser(String key, User user);

    /**
     * 以id为键
     * @param user
     */
    void setUser( User user);
    /**
     * 删除缓存的User当用户的信息修改时删除原来缓存重新缓存
     * @param uid
     */
    void delUser(long uid);

    /**
     * 按照用户名或密码删除缓存
     * @param key
     */
    void delUser(String key);
    /**
     * 根据用户id获取User
     * @param uid
     * @return
     */
    User getUser(long uid);

    /**
     * 以用户名或邮箱为键
     * @param key
     * @return
     */
    User getUser(String key);

}
