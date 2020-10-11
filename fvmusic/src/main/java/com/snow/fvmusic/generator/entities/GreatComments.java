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
@ApiModel(value = "网易云精彩评论")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("great_comments")
public class GreatComments implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("commenter_name")
    private String commenterName;
    @TableField("commenter_url")
    private String commenterUrl;
    @TableField("music_info_id")
    private Long musicInfoId;


    public static final String ID = "id";

    public static final String COMMENTER_NAME = "commenter_name";

    public static final String COMMENTER_URL = "commenter_url";

    public static final String MUSIC_INFO_ID = "music_info_id";

}