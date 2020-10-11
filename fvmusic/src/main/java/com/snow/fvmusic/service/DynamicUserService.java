package com.snow.fvmusic.service;

import com.snow.fvmusic.dto.UpdatePasswordDto;
import com.snow.fvmusic.dto.UserRegisterDto;
import com.snow.fvmusic.generator.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.MediaSize;
import java.util.Map;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-20
 * @Description：
 * @Modified By：
 * @Version:
 */
public interface DynamicUserService {
    /**
     * 用户注册
     * @param userRegisterDto
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    void register(UserRegisterDto userRegisterDto);

    /**
     * 使用用户名密码登录
     * @param username
     * @param password
     * @return token
     */
    Map<String, Object> loginByUsername(String username, String password);

    /**
     * 使用邮箱验证登录
     * @param email
     * @param code
     * @return token
     */
    Map<String, Object> loginByEmail(String email, String code);

    /**
     * 修改密码
     * @param updatePasswordDto
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    boolean updatePassword(UpdatePasswordDto updatePasswordDto);

    /**
     * 退出登录
     * @param username
     * @return
     */
    boolean logout(String username);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    User updateUserInfo(User user);

    UserDetails loadUserDetailsByUsername(String usernameOrEmail);
}
