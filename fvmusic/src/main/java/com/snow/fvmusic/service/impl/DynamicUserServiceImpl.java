package com.snow.fvmusic.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snow.fvmusic.bo.ApiException;
import com.snow.fvmusic.bo.DynamicUserDetails;
import com.snow.fvmusic.bo.ResultCode;
import com.snow.fvmusic.dto.UpdatePasswordDto;
import com.snow.fvmusic.dto.UserRegisterDto;
import com.snow.fvmusic.generator.entities.User;
import com.snow.fvmusic.generator.mapper.UserMapper;
import com.snow.fvmusic.generator.service.UserService;
import com.snow.fvmusic.service.DynamicUserService;
import com.snow.fvmusic.service.UserCacheService;
import com.snow.fvmusic.util.EmailVerifyCodeUtil;
import com.snow.fvmusic.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-20
 * @Description：
 * @Modified By：
 * @Version:
 */
@Service
@Slf4j
public class DynamicUserServiceImpl implements DynamicUserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserCacheService userCacheService;
    @Resource
    private EmailVerifyCodeUtil emailVerifyCodeUtil;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private UserService userService;

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        System.out.println(userRegisterDto);
        // 检查邮箱是否已经被注册
        if (isUserExists(userRegisterDto.getEmail(), 0)) throw new ApiException(ResultCode.ILLEGAL, "该邮箱已经被注册,请用邮箱验证登录");
        // 检查用户名是否已经被注册
        if (isUserExists(userRegisterDto.getUsername(), 1)) throw new ApiException(ResultCode.ILLEGAL, "该用户名已经被注册");
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getRepeatPassword())){
            throw new ApiException(ResultCode.ILLEGAL, "两次输入的密码不一致，请重新输入");
        }
        // 校验验证码
        boolean b = emailVerifyCodeUtil.checkCode(userRegisterDto.getEmail(), userRegisterDto.getCode());

        if (!b) throw new ApiException(ResultCode.ILLEGAL, "验证码错误或失效，请重试");
        // 注册
        User user = new User();
        user.setUsername(userRegisterDto.getUsername().replace(" ", ""));
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setCreateTime(new Date());
        userMapper.insert(user);
        log.info("Register user success");
    }

    @Override
    public Map<String, Object> loginByUsername(String username, String password) {
        // 检查用户是否存在
        if (!isUserExists(username, 1)) throw new ApiException(ResultCode.NOT_FOUND, "不存在该用户");
        UserDetails userDetails = loadUserDetailsByUsername(username);
        boolean matched = passwordEncoder.matches(password, userDetails.getPassword());
        if (!matched) throw new BadCredentialsException("密码错误");
        String token = jwtTokenUtil.generateToken(userDetails);
        // 存储authenticationToken
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        HashMap<String, Object> map = new HashMap<>();
        DynamicUserDetails dynamicUserDetails = (DynamicUserDetails)userDetails;
        map.put("userInfo",dynamicUserDetails.getUser());
        map.put("token", token);
        return map;
    }

    @Override
    public Map<String, Object> loginByEmail(String email, String code) {
        if (!isUserExists(email, 0)) throw new ApiException(ResultCode.NOT_FOUND, "改邮箱还未注册");
        boolean b = emailVerifyCodeUtil.checkCode(email, code);
        if (!b) throw new ApiException(ResultCode.ILLEGAL, "验证码错误，请重试");
        UserDetails userDetails = loadUserDetailsByUsername(email);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        HashMap<String, Object> map = new HashMap<>();
        String token = jwtTokenUtil.generateToken(userDetails);
        map.put("userInfo", ((DynamicUserDetails) userDetails).getUser());
        map.put("token", token);
        return map;
    }

    @Override
    public boolean updatePassword(UpdatePasswordDto updatePasswordDto) {
        User userDetails = userMapper.selectById(updatePasswordDto.getUid());
        if (userDetails == null) throw new ApiException(ResultCode.NOT_FOUND, "修改密码的用户不存在");
        String newPassword = updatePasswordDto.getNewPassword();
        String oldPassword = updatePasswordDto.getOldPassword();
        if (oldPassword.equals(newPassword)) throw new ApiException(ResultCode.ILLEGAL, "新密码和旧密码一样");
        boolean matches = passwordEncoder.matches(oldPassword, userDetails.getPassword());
        if (!matches) throw new ApiException(ResultCode.ILLEGAL, "旧密码输入错误");
        User user = new User();
        user.setId(updatePasswordDto.getUid());
        user.setPassword(passwordEncoder.encode(newPassword));
        userService.updateById(user);
        userCacheService.delUser(userDetails.getUsername());
        SecurityContextHolder.getContext().setAuthentication(null);
        return true;
    }

    /**
     * 退出登录，给前端返回消息，前端清除token
     * @param username
     * @return
     */
    @Override
    public boolean logout(String username) {
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(null);
        return true;
    }

    @Override
    public User updateUserInfo(User user) {
        userMapper.updateById(user);
        User updatedUser = userMapper.selectById(user.getId());
        userCacheService.delUser(updatedUser.getUsername());
        return updatedUser;
    }

    /**
     * 根据用户名过邮箱加载userDetails
     * @return
     */
    @Override
    public UserDetails loadUserDetailsByUsername(String usernameOrEmail) {
        User user = null;
        // 从redis缓存中尝试获取，获取不到再访问数据库
        user = userCacheService.getUser(usernameOrEmail);
        if (user != null) return new DynamicUserDetails(user);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        String emailPattern = ".*@.*\\.com";
        if (Pattern.matches(emailPattern, usernameOrEmail)){
            wrapper.eq("email", usernameOrEmail);
        }else{
            wrapper.eq("username", usernameOrEmail);
        }
        user = userMapper.selectOne(wrapper);
        // redis缓存user
        userCacheService.setUser(user.getUsername(), user);
        return new DynamicUserDetails(user);
    }

    /**
     * 检查用户是否已经注册
     * @param usernameOrEmail
     * @param type 0 ->检查邮箱】
     *             1 ->用户名
     * @return
     */
    private boolean isUserExists(String usernameOrEmail, int type){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        List<User> list = null;
        if (type == 1){
            wrapper.eq("username", usernameOrEmail);
        }else {
            wrapper.eq("email", usernameOrEmail);
        }
        list = userMapper.selectList(wrapper);
        return !CollUtil.isEmpty(list);
    }
}
