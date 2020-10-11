package com.snow.fvmusic.generator.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;

/**
 * @author snow
 * @Description:
 * @since 2020-09-19
 */
@ApiModel(value = "用户实体")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("qq")
    private Integer qq;
    @TableField("email")
    @Email
    private String email;
    @ApiModelProperty(value = "网易云音乐主页")
    @TableField("wangyi_music_url")
    private String wangyiMusicUrl;
    @TableField("age")
    private Integer age;
    @TableField("birthday")
    private Date birthday;
    @TableField("create_time")
    private Date createTime;
    @ApiModelProperty(value = "等级")
    @TableField("level")
    private Integer level;
    @ApiModelProperty(value = "职业")
    @TableField("profession")
    private String profession;
    @ApiModelProperty(value = "擅长")
    @TableField("skill")
    private String skill;
    @ApiModelProperty(value = "关注数量")
    @TableField("concern_count")
    private Integer concernCount;
    @ApiModelProperty(value = "粉丝数")
    @TableField("fans_count")
    private Integer fansCount;
    @ApiModelProperty(value = "发布的文章数")
    @TableField("article_count")
    private Integer articleCount;
    @ApiModelProperty(value = "个人主页背景图片")
    @TableField("background_url")
    private String backgroundUrl;
    @TableField("avatar_url")
    @ApiModelProperty("头像链接")
    private String avatarUrl;
    private String sign;
    private String favouriteMusicStyles;
    private Integer createPools;
    public static final String ID = "id";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String QQ = "qq";

    public static final String EMAIL = "email";

    public static final String WANGYI_MUSIC_URL = "wangyi_music_url";

    public static final String AGE = "age";

    public static final String BIRTHDAY = "birthday";

    public static final String CREATE_TIME = "create_time";

    public static final String LEVEL = "level";

    public static final String FAVOURITE_MUSIC_STYLE_ID = "favourite_music_style_id";

    public static final String PROFESSION = "profession";

    public static final String SKILL = "skill";

    public static final String CONCERN_COUNT = "concern_count";

    public static final String FANS_COUNT = "fans_count";

    public static final String ARTICLE_COUNT = "article_count";

    public static final String BACKGROUND_URL = "background_url";

    public static final String AVATAR_URL =  "avatar_url";

}