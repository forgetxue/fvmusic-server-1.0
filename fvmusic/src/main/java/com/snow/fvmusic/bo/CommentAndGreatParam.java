package com.snow.fvmusic.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author ：snow
 * @Date ：Created in 2020-10-06
 * @Description：
 * @Modified By：
 * @Version:
 */
@Data
public class CommentAndGreatParam {
    @NotNull
    private Long articleId;
    @NotNull
    private String articleName;
    @NotNull
    private Long userId;
    @NotNull
    private String username;
    @NotNull
    private Long articleUserId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;
    private String message;
}
