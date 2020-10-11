package com.snow.fvmusic.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snow.fvmusic.bo.ApiException;
import com.snow.fvmusic.bo.CommentAndGreatParam;
import com.snow.fvmusic.bo.ResultCode;
import com.snow.fvmusic.dao.ArticleVODao;
import com.snow.fvmusic.dao.CommentVODao;
import com.snow.fvmusic.dto.ArticleDto;
import com.snow.fvmusic.generator.entities.Article;
import com.snow.fvmusic.generator.entities.Comment;
import com.snow.fvmusic.generator.entities.MusicInfo;
import com.snow.fvmusic.generator.entities.User;
import com.snow.fvmusic.generator.mapper.*;
import com.snow.fvmusic.service.ArticleVOService;
import com.snow.fvmusic.service.RedisService;
import com.snow.fvmusic.vo.ArticleVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-28
 * @Description：
 * @Modified By：
 * @Version:
 */
@Service
public class ArticleVOServiceImpl implements ArticleVOService {
    @Resource
    private CommentVODao commentVODao;
    @Resource
    private ArticleVODao articleVODao;
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private MusicInfoMapper musicInfoMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisService redisService;
    @Resource
    private CommentMapper commentMapper;

    public final String  MESSAGE_COMMENT_REDIS_KEY_PRE = "MESSAGE_COMMENT_";
    public final String MESSAGE_GREAT_REDIS_KEY_PRE = "MESSAGE_GREAT_";
    private HashSet<String> orderFields;
    private HashSet<String> order;
    {
        orderFields = new HashSet<>();
        order = new HashSet<>();
        orderFields.add("great_count".intern());
        orderFields.add("views".intern());
        orderFields.add("create_datetime".intern());
        orderFields.add("comment_count".intern());
        order.add("desc".intern());
        order.add("DESC".intern());
        order.add("ASC".intern());
        order.add("asc".intern());
    }

    @Override
    public PageInfo<ArticleVO> getArticlesOfMusicPool(Long poolId, Integer current, Integer size) {
        List<ArticleVO> articlesVOPage = articleVODao.getArticlesVOPage(poolId, null, null);
        // 更新文章的views
        List<Long> ids = articlesVOPage.stream().map(ArticleVO::getId).collect(Collectors.toList());
        if (!CollUtil.isEmpty(ids)){
            articleVODao.incrViewsBatch(ids);
        }
        PageHelper.startPage(current, size);
        return new PageInfo<>(articlesVOPage);
    }

    @Override
    public PageInfo<ArticleVO> getArticleOfMusicPoolByOrder(Long poolId, String orderField, String order, Integer current, Integer size) {
        if (!this.orderFields.contains(orderField) || !this.order.contains(order)) throw new ApiException(ResultCode.ILLEGAL, "不存在该字段或排序规则");
        List<ArticleVO> list = articleVODao.getArticlesVOPage(poolId, orderField, order);
        PageHelper.startPage(current, size);
        return new PageInfo<>(list);
    }

    @Override
    public int create(ArticleDto articleDto) {
        Article article = new Article();
        // 存储音乐信息
        Integer wyId = Integer.valueOf(articleDto.getWangYiMusicId());
        QueryWrapper<MusicInfo> musicInfoWrapper = new QueryWrapper<>();
        musicInfoWrapper.eq("wangyi_id", wyId);
        MusicInfo musicInfo = musicInfoMapper.selectOne(musicInfoWrapper);
//        已经存在
        if (musicInfo != null){
            article.setMusicInfoId(musicInfo.getId());
        }else{
            // 插入musicInfo
            MusicInfo insertMusicInfo = new MusicInfo();
            insertMusicInfo.setWangyiId(wyId);
            musicInfoMapper.insert(insertMusicInfo);
//            获取刚插入的id
            musicInfoWrapper.clear();
            musicInfoWrapper.inSql("id", "select max(id) from music_info");
            List<Object> list = musicInfoMapper.selectObjs(musicInfoWrapper);
            if (CollUtil.isEmpty(list)){
                throw new ApiException(ResultCode.SERVER_ERROR, "music info id 获取异常");
            }
            Long musicInfoId = (Long) list.get(0);
            article.setMusicInfoId(musicInfoId);
        }
        article.setName(articleDto.getName());
        article.setContent(articleDto.getContent());
        article.setUserId(articleDto.getUserId());
        article.setPoolId(articleDto.getPoolId());
        article.setPhotosUrl(articleDto.getPhotosUrl());
        article.setCreateDatetime(LocalDateTime.now());
        int insert = articleMapper.insert(article);
        User user = userMapper.selectById(articleDto.getUserId());
        user.setArticleCount(user.getArticleCount() + 1);
        userMapper.updateById(user);
        return insert;
    }

    @Override
    public ArticleVO getArticleVOById(Long id) {
        if (id == null) throw new  ApiException(ResultCode.ILLEGAL, "参数Id不能为空");
        ArticleVO articleById = articleVODao.getArticleById(id);
        if (articleById != null) articleVODao.incrViews(id);
        return articleById;
    }

    /**
     * 添加评论的同时，生成点赞消息存储在redis中
     * @param param
     * @return
     */
    @Override
    public int makeComment(CommentAndGreatParam param) {
        Comment comment = new Comment();
        comment.setArticleId(param.getArticleId());
        comment.setContent(param.getContent());
        comment.setLevel(0);
        comment.setCreateDatetime(LocalDateTime.now());
        comment.setUserId(param.getUserId());
        int insert = commentMapper.insert(comment);
        if (insert > 0) {
            generateSocketMessage(param);
            articleVODao.incrCommentCount(param.getArticleId());
        }

        return insert;
    }

    @Override
    public int makeGreat(CommentAndGreatParam param) {
        articleVODao.incrGreatCount(param.getArticleId());
        generateSocketMessage(param);
        return 1;
    }
    private void generateSocketMessage(CommentAndGreatParam param){
        param.setDate(new Date());
        if (StrUtil.isEmpty(param.getContent())){
            param.setMessage(param.getUsername() + "赞了您的分型" + param.getArticleName());
            redisService.lPush(MESSAGE_GREAT_REDIS_KEY_PRE + param.getArticleUserId(), param);
        }else{
            param.setMessage(param.getUsername() + "评论了您的分享" + param.getArticleName());
            redisService.lPush(MESSAGE_COMMENT_REDIS_KEY_PRE + param.getArticleUserId(), param);
        }

    }
}
