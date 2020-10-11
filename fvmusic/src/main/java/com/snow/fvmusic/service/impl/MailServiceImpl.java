package com.snow.fvmusic.service.impl;

import com.snow.fvmusic.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-20
 * @Description：
 * @Modified By：
 * @Version:
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender mailSender;


    public void sendVerifyCode(String to, String title, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); //发送人
        message.setTo(to);   //收件人
        message.setSubject(title);  //邮件名
        message.setText(content);   //邮件内容（验证码）
        mailSender.send(message);
        log.info("已经发送" + content);
    }
}
