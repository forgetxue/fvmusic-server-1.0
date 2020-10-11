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
public class UpdatePasswordDto {
    @NotEmpty
    private String newPassword;
    @NotEmpty
    private String oldPassword;
    private Long uid;

}
