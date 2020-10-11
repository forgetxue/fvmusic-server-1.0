package com.snow.fvmusic.dto;

import com.snow.fvmusic.generator.entities.MusicPool;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-19
 * @Description：
 * @Modified By：
 * @Version:
 */
@Data
@ApiModel("music pool 传输对象")
public class MusicPoolDto {
    private MusicPool musicPool;
    private long createUserId;
    private String createUsername;
    private String musicTypeName;
    private Long musicStyleId;
    private Integer wangyiMusicId;
}
