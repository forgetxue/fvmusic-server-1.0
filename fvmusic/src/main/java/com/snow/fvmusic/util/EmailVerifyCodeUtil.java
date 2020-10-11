package com.snow.fvmusic.util;

import com.snow.fvmusic.service.MailService;
import com.snow.fvmusic.service.RedisService;
import com.sun.corba.se.impl.encoding.CachedCodeBase;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-21
 * @Description：
 * @Modified By：
 * @Version:
 */
@Component
public class EmailVerifyCodeUtil {
    @Resource
    private  RedisService redisService;
    @Resource
    private  MailService mailService;

    private static final String words = "qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM1234567890";

    /**
     * 生成验证码
     * @return
     */
    private String createCode(){
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        int wordLength = words.length();
        for (int i = 0; i < 6; i ++ ){
            buffer.append(words.charAt(random.nextInt(wordLength)));
        }
        return buffer.toString();
    }

    /**
     * 检查验证码是否正确
     * @param code
     * @return
     */
    public boolean checkCode(String email, String code){
        String cacheCode = (String) redisService.get(email);
        if (StringUtil.isNullOrEmpty(cacheCode))  return false;
        return cacheCode.equals(code);
    }

    /**
     * 发送验证码
     * @param email
     * @param expired
     */
    public  void sendCode(String email, long expired){
        String code = createCode();
        String context = "亲爱的FvMusic用户，您的验证码为：" + "\n" + code + "\n" + "有效期为" + expired + "秒";
        mailService.sendVerifyCode(email, "FvMusic验证码", context);
        redisService.set(email, code, expired);
    }

}
