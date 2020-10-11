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
@ApiModel(value = "musicPool推荐音乐信息")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("recommend_music_style_relation")
public class RecommendMusicStyleRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("music_info_id")
    private Long musicInfoId;
    @TableField("music_style_id")
    private Long musicStyleId;


    public static final String ID = "id";

    public static final String MUSIC_INFO_ID = "music_info_id";

    public static final String MUSIC_STYLE_ID = "music_style_id";

}