package com.snow.fvmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snow.fvmusic.generator.entities.Article;
import com.snow.fvmusic.generator.entities.MusicPool;
import com.snow.fvmusic.generator.mapper.ArticleMapper;
import com.snow.fvmusic.generator.mapper.MusicPoolMapper;
import com.snow.fvmusic.service.RedisService;
import com.snow.fvmusic.service.UserPageVOService;
import com.snow.fvmusic.vo.UserPageArticlesAndPoolsVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ：snow
 * @Date ：Created in 2020-10-05
 * @Description：
 * @Modified By：
 * @Version:
 */
@Service
public class UserPageVOServiceImpl implements UserPageVOService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private MusicPoolMapper musicPoolMapper;
    @Resource
    private RedisService redisService;
    @Value("${redis.message.comment}")
    private String MESSAGE_COMMENT_PRE_KEY;
    @Value("${redis.message.great}")
    private String MESSAGE_GREAT_PRE_KEY;
    @Override
    public UserPageArticlesAndPoolsVO userPageArticlesAndPool(Long userId) {
        // ArticleList
        QueryWrapper<Article> articleWrapper = new QueryWrapper<>();
        articleWrapper.select("id", "name", "great_count", "comment_count");
        articleWrapper.eq("user_id", userId);
        List<Article> articles = articleMapper.selectList(articleWrapper);
        // poolList
        QueryWrapper<MusicPool> poolWrapper = new QueryWrapper<>();
        poolWrapper.eq("create_user_id", userId);
        poolWrapper.select("cover_url", "create_datetime", "id", "name", "article_count");
        List<MusicPool> pools = musicPoolMapper.selectList(poolWrapper);
        ArrayList<UserPageArticlesAndPoolsVO.SimpleArticle> simpleArticleArrayList = new ArrayList<>();
        ArrayList<UserPageArticlesAndPoolsVO.SimplePool> simplePoolList = new ArrayList<>();
        // simpleArticleList add
        articles.forEach(article ->{
            UserPageArticlesAndPoolsVO.SimpleArticle simpleArticle = new UserPageArticlesAndPoolsVO.SimpleArticle();
            simpleArticle.setArticleId(article.getId());
            simpleArticle.setArticleName(article.getName());
            simpleArticle.setCommentsCount(article.getCommentCount());
            simpleArticle.setGreatCount(article.getGreatCount());
            simpleArticleArrayList.add(simpleArticle);
        });
        // simplePoolList add
        pools.forEach(pool -> {
            UserPageArticlesAndPoolsVO.SimplePool simplePool = new UserPageArticlesAndPoolsVO.SimplePool();
            simplePool.setPoolId(pool.getId());
            simplePool.setName(pool.getName());
            simplePool.setCoverUrl(pool.getCoverUrl());
            simplePool.setArticlesCount(pool.getArticleCount());
            simplePool.setCreateDateTime(pool.getCreateDateTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            simplePoolList.add(simplePool);
        });
        // vo
        UserPageArticlesAndPoolsVO vo = new UserPageArticlesAndPoolsVO();
        vo.setArticles(simpleArticleArrayList);
        vo.setPools(simplePoolList);

        return vo;
    }

    @Override
    public Map<String, Object> userMessage(Long userId) {
        HashMap<String, Object> resultMap = new HashMap<>();
        List<Object> comments = redisService.lRange(MESSAGE_COMMENT_PRE_KEY, 0, -1);
        List<Object> great = redisService.lRange(MESSAGE_GREAT_PRE_KEY, 0, -1);
        return null;
    }
}
