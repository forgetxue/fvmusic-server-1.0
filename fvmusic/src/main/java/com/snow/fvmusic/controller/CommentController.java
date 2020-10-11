package com.snow.fvmusic.controller;

import com.snow.fvmusic.bo.CommonResult;
import com.snow.fvmusic.bo.ResultCode;
import com.snow.fvmusic.generator.service.CommentService;
import com.snow.fvmusic.service.CommentVOService;
import com.snow.fvmusic.vo.CommentVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-28
 * @Description：
 * @Modified By：
 * @Version:
 */
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Resource
    private CommentVOService commentVOService;
    @Resource
    private CommentService commentService;

    @GetMapping("/limit")
    public CommonResult getCommentsOfArticleLimit(
            @RequestParam(name="ArticleId", required = true) Long articleId,
            @RequestParam(name="limit", defaultValue = "10", required = false) Integer limit
    ){
        List<CommentVO> commentVO = commentVOService.getCommentVO(articleId, limit);
        return commentVO == null ? CommonResult.failed(ResultCode.SERVER_ERROR, "数据库错误") : CommonResult.success(commentVO);
    }
    @GetMapping("/all")
    public CommonResult getAllCommentsOfArticles(@RequestParam(name="articleId", required = true) Long articleId){

        List<CommentVO> list = commentVOService.getAllCommentVO(articleId);
        return list == null ? CommonResult.failed(ResultCode.SERVER_ERROR, "数据库错误") : CommonResult.success(list);
    }
}
