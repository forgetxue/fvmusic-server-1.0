package com.snow.fvmusic.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.snow.fvmusic.bo.CommonResult;
import com.snow.fvmusic.bo.ResultCode;
import com.snow.fvmusic.bo.WebConst;
import com.snow.fvmusic.dto.UpdatePasswordDto;
import com.snow.fvmusic.dto.UserRegisterDto;
import com.snow.fvmusic.generator.entities.User;
import com.snow.fvmusic.service.DynamicUserService;
import com.snow.fvmusic.util.EmailVerifyCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-21
 * @Description：
 * @Modified By：
 * @Version:
 */
@RestController
@Api("登录注册接口")
public class UserController {
    @Resource
    private DynamicUserService dynamicUserService;
    @Resource
    private EmailVerifyCodeUtil emailVerifyCodeUtil;

    @PostMapping("/register")
    @ApiOperation("注册-json")
    public CommonResult register(@RequestBody UserRegisterDto dto, BindingResult bindingResult){
        dynamicUserService.register(dto);
        return CommonResult.success("注册成功");
    }
    @PostMapping("/login")
    @ApiOperation("使用用户名密码等了")
    public CommonResult loginByUsername(
            @RequestParam(name="username", required = true) String username,
            @RequestParam(name="password", required = true) String password
    ){
        Map<String, Object> result = dynamicUserService.loginByUsername(username, password);
        return CollUtil.isEmpty(result) ? CommonResult.failed(ResultCode.SERVER_ERROR, "登录失败，服务器内部错误")
                : CommonResult.success("登录成功",result);
    }

    @PostMapping("/code")
    @ApiOperation("获取验证码")
    public String getCode(@RequestParam(name="email", required = true) String email){
        emailVerifyCodeUtil.sendCode(email, WebConst.EMAIL_VERIFY_CODE_EXPIRED);
        return "验证码已发送";
    }
    @PostMapping("/loginByEmail")
    @ApiOperation("使用邮箱登录")
    public CommonResult loginByEmail(
            @RequestParam(name="email", required = true) String email,
            @RequestParam(name="code", required = true) String code
    ){
        Map<String, Object> result = dynamicUserService.loginByEmail(email, code);
        return CollUtil.isEmpty(result) ? CommonResult.failed(ResultCode.SERVER_ERROR, "登录失败，服务器内部错误")
                :CommonResult.success("登录成功", result);
    }

    @PutMapping("/updateUserInfo")
    @ApiOperation("更新用户信息—json")
    public CommonResult update(@RequestBody User user){
        User updatedUser = dynamicUserService.updateUserInfo(user);
        return updatedUser != null ? CommonResult.success("更新成功", updatedUser) :
                CommonResult.failed(ResultCode.SERVER_ERROR, "更新失败，服务器内部错误");
    }

    @PostMapping("/updatePassword")
    @ApiOperation("更新密码-json")
    public CommonResult updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto, BindingResult bindingResult){
        boolean b = dynamicUserService.updatePassword(updatePasswordDto);
        return b ? CommonResult.success("更新成功，请重新登录"): CommonResult.failed(ResultCode.SERVER_ERROR, "更新失败，服务器内部错误");
    }
}
