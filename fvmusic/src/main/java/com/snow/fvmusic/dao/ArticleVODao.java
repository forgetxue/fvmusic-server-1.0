package com.snow.fvmusic.dao;

import com.snow.fvmusic.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-28
 * @Description：
 * @Modified By：
 * @Version:
 */
@Mapper
public interface ArticleVODao {
    List<ArticleVO> getArticlesVOPage(@Param("poolId")Long poolId, @Param("orderBy")String OrderBy, @Param("order") String order);

    ArticleVO getArticleById(Long id);

    void incrCommentCount(Long articleId);
    void incrGreatCount(Long articleId);
    void incrViews(Long articleId);
    void incrViewsBatch(List<Long> articleIds);
}
