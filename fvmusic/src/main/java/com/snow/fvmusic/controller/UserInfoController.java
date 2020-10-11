package com.snow.fvmusic.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snow.fvmusic.bo.CommonResult;
import com.snow.fvmusic.bo.ResultCode;
import com.snow.fvmusic.generator.entities.Article;
import com.snow.fvmusic.generator.entities.MusicPool;
import com.snow.fvmusic.generator.entities.User;
import com.snow.fvmusic.generator.service.ArticleService;
import com.snow.fvmusic.generator.service.MusicPoolService;
import com.snow.fvmusic.generator.service.UserService;
import com.snow.fvmusic.service.UserCacheService;
import com.snow.fvmusic.service.UserPageVOService;
import com.snow.fvmusic.vo.UserPageArticlesAndPoolsVO;
import com.sun.mail.imap.protocol.UIDSet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.lang.UsesSunMisc;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-30
 * @Description：
 * @Modified By：
 * @Version:
 */
@RestController
@RequestMapping("/userInfo")
@Api("用户个人主页信息展示接口")
public class UserInfoController {
    @Resource
    private UserService userService;
    @Resource
    private ArticleService articleService;
    @Resource
    private MusicPoolService musicPoolService;
    @Resource
    private UserPageVOService userPageVOService;
    @Resource
    private UserCacheService userCacheService;

    @GetMapping("/{id}")
    @ApiOperation("根据用户Id获取用户信息")
    public CommonResult getUserInfoById(@PathVariable("id") Long id){
        User user = userService.getById(id);
        if(user != null){
            user.setPassword(null);
            return CommonResult.success(user);
        }
        return CommonResult.failed(ResultCode.NOT_FOUND, "不存在该用户");
    }
    @GetMapping("/byUsername/{username}")
    @ApiOperation("根据用户名搜索用户")
    public CommonResult getUserInfoByUsername(@PathVariable("username") String username){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userService.getOne(wrapper);
        if (user != null){
            user.setPassword(null);
            return CommonResult.success(user);
        }
        return CommonResult.failed(ResultCode.NOT_FOUND, "不存在该用户");
    }
    @GetMapping("/articles/{userId}")
    @ApiOperation("获取指定用户的文章列表")
    public CommonResult getUserArticles(@PathVariable("userId") Long userId){
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name", "great_count", "comment_count");
        wrapper.eq("user_id", userId);
        List<Article> list = articleService.list(wrapper);
        return list != null ? CommonResult.success(list) : CommonResult.failed(ResultCode.SERVER_ERROR, "服务器内部错误");
    }

    @GetMapping("/pools/{userId}")
    @ApiOperation("获取用户的音乐池")
    public CommonResult getPoolsOfUser(@PathVariable("userId") Long userId){
        QueryWrapper<MusicPool> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name","create_datetime", "article_count", "cover_url");
        wrapper.eq("create_user_id", userId);
        List<MusicPool> list = musicPoolService.list(wrapper);
        return CommonResult.success(list);
    }

    @PostMapping("/updateAvatar")
    @ApiOperation("更新头像")
    public CommonResult updateAvatar(@RequestParam(name="uId", required = true) Long uid,
                               @RequestParam(name="avatarUrl", required = true) String url){
        String regex = "(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\&%\\+\\$#_=]*)?";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(url.trim());
        boolean isMatch = mat.matches();
        if (isMatch){
            User user = new User();
            user.setId(uid);
            user.setAvatarUrl(url);
            userService.updateById(user);
            return CommonResult.success("头像上传成功",url);
        }else{
            return CommonResult.failed(ResultCode.ILLEGAL, "头像的url有误");
        }
    }
    @PostMapping("/update")
    @ApiOperation("更新用户信息")
    public CommonResult updateUserInfo(@RequestBody User user){
        boolean b = userService.updateById(user);
        if (b){
            User userRes = userService.getById(user.getId());
            return CommonResult.success("跟新成功！", userRes);
        }else{
            return CommonResult.failed(ResultCode.SERVER_ERROR, "跟新失败，服务器错误");
        }
    }
    @PostMapping("/uploadBackground")
    @ApiOperation("上传主页背景图片")
    public CommonResult uploadBackground(
            @RequestParam(name="url", required = true) String url,
            @RequestParam(name="uid", required = true) Long uid,
            @RequestParam(name="username", required = true) String username,
            @RequestParam(name="email", required = true) String email
    ){
        User user = new User();
        user.setBackgroundUrl(url);
        user.setId(uid);
        userCacheService.delUser(username);
        return userService.updateById(user) ? CommonResult.success("上传成功") : CommonResult.failed("上传失败");
    }
    @GetMapping("/poolsAndArticles/{uId}")
    @ApiOperation("用户主页的article 和pool 的vo")
    public CommonResult userPagePoolsAndArticleVo(@PathVariable("uId") Long uId){
        if (uId == null) return CommonResult.failed(ResultCode.ILLEGAL, "参数Uid不能为空");
        UserPageArticlesAndPoolsVO result = userPageVOService.userPageArticlesAndPool(uId);
        return CommonResult.success(result);
    }
}
