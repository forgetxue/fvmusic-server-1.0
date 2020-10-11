package com.snow.fvmusic.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-26
 * @Description：
 * @Modified By：
 * @Version:
 */
@Data
public class CommentVO {
    private String username;
    private Long userId;
    private String userAvatarUrl;
    private String content;
    private LocalDateTime createDatetime;
    private List<CommentVO> childrenComments;

}
