package com.snow.fvmusic.vo;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author ：snow
 * @Date ：Created in 2020-10-05
 * @Description：
 * @Modified By：
 * @Version:
 */
@Data
public class UserPageArticlesAndPoolsVO {
    private List<SimpleArticle> articles;
    private List<SimplePool> pools;

    @Data
    public static  class SimpleArticle{
        private Long articleId;
        private String articleName;
        private Integer greatCount;
        private Integer commentsCount;
    }
    @Data
    public static class SimplePool{
        private Long poolId;
        private String name;
        private String coverUrl;
        private Integer articlesCount;
        // 时间戳
        private Long createDateTime;

    }
}
