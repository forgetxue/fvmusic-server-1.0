package com.snow.fvmusic.generator.service.impl;

import com.snow.fvmusic.generator.entities.Comment;
import com.snow.fvmusic.generator.mapper.CommentMapper;
import com.snow.fvmusic.generator.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author snow
 * @since 2020-09-19
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
