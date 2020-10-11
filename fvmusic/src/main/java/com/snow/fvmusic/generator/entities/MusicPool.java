package com.snow.fvmusic.generator.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

/**
 * @author snow
 * @Description:
 * @since 2020-09-19
 */
@ApiModel(value = "musicPool实体类")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("music_pool")
public class MusicPool implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "音乐池名字")
    @TableField("name")
    @NotEmpty
    private String name;
    @ApiModelProperty(value = "介绍")
    @TableField("introduce")
    @NotEmpty
    private String introduce;
    @TableField("article_count")
    private Integer articleCount;
    @ApiModelProperty(value = "近期活跃用户数量")
    @TableField("recent_user_count")
    private Integer recentUserCount;
    @ApiModelProperty(value = "背景图片")
    @TableField("cover_url")
    @URL
    private String coverUrl;
    @ApiModelProperty(value = "音乐池代表音乐，由创始人设置")
    @TableField("main_music_info")
    private Long mainMusicInfo;
    @ApiModelProperty(value = "创始人id")
    @TableField("create_user_id")
    private Long createUserId;
    @TableField("music_type_id")
    @NotEmpty
    private Long musicTypeId;
    @ApiModelProperty(value = "访客数量")
    @TableField("gust_count")
    private Integer gustCount;
    @TableField("create_datetime")
    private LocalDateTime createDateTime;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String INTRODUCE = "introduce";

    public static final String ARTICLE_COUNT = "article_count";

    public static final String RECENT_USER_COUNT = "recent_user_count";

    public static final String COVER_URL = "cover_url";

    public static final String MAIN_MUSIC_INFO = "main_music_info";

    public static final String CREATER_USER_ID = "creater_user_id";

    public static final String MUSIC_TYPE_ID = "music_type_id";

    public static final String GUST_COUNT = "gust_count";

}