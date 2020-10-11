package com.snow.fvmusic.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author ：snow
 * @Date ：Created in 2020-10-03
 * @Description：
 * @Modified By：
 * @Version:
 */
@Data
public class PoolDto {
    @NotNull
    private String name;
    @NotNull
    private String introduce;
    @NotNull
    private String mainMusicUrl;
    @NotNull
    private Long musicTypeId;
    private String coverUrl;
    @NotNull
    private Long createUserId;
}
