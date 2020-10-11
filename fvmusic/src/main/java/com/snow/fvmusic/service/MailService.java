package com.snow.fvmusic.service;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-21
 * @Description：
 * @Modified By：
 * @Version:
 */
public interface MailService {
    void sendVerifyCode(String to, String title, String content);
}
