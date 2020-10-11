package com.snow.fvmusic.generator.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value = "音乐信息实体类")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("music_info")
public class MusicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "网易云音乐id")
    @TableField("wangyi_id")
    private Integer wangyiId;
    @ApiModelProperty(value="音乐类型id")
    @TableField("style_id")
    private Long styleId;


    public static final String ID = "id";


    public static final String WANGYI_ID = "wangyi_id";


    public static final String STYLE_ID = "style_id";

}