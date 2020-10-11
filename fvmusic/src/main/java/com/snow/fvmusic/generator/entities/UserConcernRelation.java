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
@ApiModel(value = "user关注关联实体")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_concern_relation")
public class UserConcernRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("user_id")
    private Integer userId;
    @TableField("concern_user_id")
    private Integer concernUserId;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String CONCERN_USER_ID = "concern_user_id";

}