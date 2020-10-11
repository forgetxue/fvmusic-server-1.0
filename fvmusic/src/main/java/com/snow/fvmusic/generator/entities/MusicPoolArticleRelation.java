package com.snow.fvmusic.generator.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author snow
 * @Description:
 * @since 2020-09-19
 */
@ApiModel(value = "musicPool-article关联实体类")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("music_pool_article_relation")
public class MusicPoolArticleRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("article_id")
    private Long articleId;
    @TableField("music_pool_id")
    private Long musicPoolId;


    public static final String ID = "id";

    public static final String ARTICLE_ID = "article_id";

    public static final String MUSIC_POOL_ID = "music_pool_id";

}