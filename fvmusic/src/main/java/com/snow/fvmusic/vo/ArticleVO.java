package com.snow.fvmusic.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-26
 * @Description：
 * @Modified By：
 * @Version:
 */
@Data
@ApiModel("文章视图")
public class ArticleVO {
    private Long id;
    private String name;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime articleCreatDateTime;
    private String photosUrl;
    private String username;
    @ApiModelProperty("音乐网易云的id")
    private String musicWYId;
    private String userAvatarUrl;
    private Long userId;
    private int commentCount;
    private int greatCount;
    private int views;
    private List<CommentVO> comments;
}
