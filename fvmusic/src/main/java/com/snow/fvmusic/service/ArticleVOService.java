package com.snow.fvmusic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.snow.fvmusic.bo.CommentAndGreatParam;
import com.snow.fvmusic.dto.ArticleDto;
import com.snow.fvmusic.vo.ArticleVO;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-26
 * @Description：
 * @Modified By：
 * @Version:
 */
public interface ArticleVOService {

    /**
     * 获取音乐池中的文章分页
     * @param poolId
     * @return
     */
    PageInfo<ArticleVO> getArticlesOfMusicPool(Long poolId, Integer current, Integer size);

    /**
     * 获取指定排序的文章分页
     * @param poolId
     * @param orderField
     * @Param order : 顺序（默认） or 逆序
     * @return
     */
    PageInfo<ArticleVO> getArticleOfMusicPoolByOrder(Long poolId,String orderField, String order, Integer current, Integer size);

    /**
     * 发布文章
     * @param
     * @return
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    int create(ArticleDto articleDto);

    /**
     * 根据id获取文章
     * @param id
     * @return
     */
    ArticleVO getArticleVOById(Long id);

    /**
     * 评论
     * @param param
     * @return
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    int makeComment(CommentAndGreatParam param);

    /**
     * 点赞
     * @param param
     * @return
     */
    int makeGreat(CommentAndGreatParam param);
}
