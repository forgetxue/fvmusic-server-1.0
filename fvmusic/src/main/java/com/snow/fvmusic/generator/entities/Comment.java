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
 * @since 2020-09-19
 */
@ApiModel(value = "评论实体类")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty(value = "评论内容")
    @TableField("content")
    private String content;
    @TableField("create_datetime")
    private LocalDateTime createDatetime;
    @ApiModelProperty(value = "评论分级 0 ->一级评论；1->二级评论")
    @TableField("level")
    private Integer level;
    @TableField("article_id")
    private Long articleId;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String CONTENT = "content";

    public static final String CREATE_DATETIME = "create_datetime";

    public static final String LEVEL = "level";

    public static final String ARTICLE_ID = "article_id";

}