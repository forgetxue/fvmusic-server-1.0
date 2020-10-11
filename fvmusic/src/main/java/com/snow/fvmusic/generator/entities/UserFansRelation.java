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
@ApiModel(value = "用户粉丝关联")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_fans_relation")
public class UserFansRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("user_id")
    private Integer userId;
    @TableField("fans_id")
    private Integer fansId;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String FANS_ID = "fans_id";

}