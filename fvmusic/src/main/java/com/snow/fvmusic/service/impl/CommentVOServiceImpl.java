package com.snow.fvmusic.service.impl;

import com.snow.fvmusic.dao.CommentVODao;
import com.snow.fvmusic.service.CommentVOService;
import com.snow.fvmusic.vo.CommentVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-28
 * @Description：
 * @Modified By：
 * @Version:
 */
@Service
public class CommentVOServiceImpl implements CommentVOService {
    @Resource
    private CommentVODao commentVODao;
    @Override
    public List<CommentVO> getAllCommentVO(Long articleIds) {
        return commentVODao.getCommentsOfArticle(articleIds, null);

    }

    @Override
    public List<CommentVO> getCommentVO(Long articleId, Integer size) {
        return commentVODao.getCommentsOfArticle(articleId, size);
    }
}
