package com.snow.fvmusic.generator.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author snow
 * @Description:
 * @since 2020-09-26
 */
@ApiModel(value = "")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "文章内容")
    @TableField("content")
    private String content;
    @TableField("create_datetime")
    private LocalDateTime createDatetime;
    @ApiModelProperty(value = "文章内容、音乐评论、吉他谱分享")
    @TableField("article_type_id")
    private Long articleTypeId;
    @ApiModelProperty(value = "配图url以;隔开")
    @TableField("photos_url")
    private String photosUrl;
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty(value = "音乐信息id")
    @TableField("music_info_id")
    private Long musicInfoId;
    @ApiModelProperty(value = "评论数")
    @TableField("comment_count")
    private Integer commentCount;
    @ApiModelProperty(value = "点赞数")
    @TableField("great_count")
    private Integer greatCount;
    @TableField("pool_id")
    private Long poolId;
    @TableField("views")
    private Integer views;

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String CONTENT = "content";

    public static final String CREATE_DATETIME = "create_datetime";

    public static final String ARTICLE_TYPE_ID = "article_type_id";

    public static final String PHOTOS_URL = "photos_url";

    public static final String USER_ID = "user_id";

    public static final String MUSIC_INFO_ID = "music_info_id";

    public static final String COMMENT_COUNT = "comment_count";

    public static final String GREAT_COUNT = "great_count";
    public static final String POOL_ID =  "pool_id";

    public static final String VIEWS = "views";

}