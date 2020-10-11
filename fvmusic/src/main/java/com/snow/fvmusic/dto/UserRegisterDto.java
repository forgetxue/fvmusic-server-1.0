package com.snow.fvmusic.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-20
 * @Description：
 * @Modified By：
 * @Version:
 */
@Data
public class UserRegisterDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String repeatPassword;
    @Email
    private String email;
    @NotEmpty
    private String code;
}
