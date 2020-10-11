package com.snow.fvmusic.dao;

import com.snow.fvmusic.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-26
 * @Description：
 * @Modified By：
 * @Version:
 */
@Mapper
public interface CommentVODao {
    List<CommentVO> getCommentsOfArticle(@Param("articleId")Long articleId, @Param("limit") Integer limit);
}
