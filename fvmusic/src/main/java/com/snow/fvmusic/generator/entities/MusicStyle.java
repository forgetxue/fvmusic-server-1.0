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
@ApiModel(value = "音乐分类")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("music_style")
public class MusicStyle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "音乐分类")
    @TableField("name")
    private String name;

    public static final String ID = "id";

    public static final String NAME = "name";



}