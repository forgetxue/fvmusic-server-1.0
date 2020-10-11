package com.snow.fvmusic.service;

import com.snow.fvmusic.bo.CommentAndGreatParam;
import com.snow.fvmusic.vo.CommentVO;

import java.util.List;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-26
 * @Description：
 * @Modified By：
 * @Version:
 */
public interface CommentVOService {
    /**
     * 获取对应文章的comments
     * @param articleId
     * @return
     */
    List<CommentVO> getAllCommentVO(Long articleId);

    List<CommentVO> getCommentVO(Long articleId, Integer size);

}
