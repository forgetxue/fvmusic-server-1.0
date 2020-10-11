package com.snow.fvmusic.dto;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author ：snow
 * @Date ：Created in 2020-10-03
 * @Description：
 * @Modified By：
 * @Version:
 */
@Data
public class ArticleDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String content;
    private String photosUrl;
    @NotEmpty
    private String wangYiMusicId;
    @NotNull
    private Long userId;
    @NotNull
    private Long poolId;

}
