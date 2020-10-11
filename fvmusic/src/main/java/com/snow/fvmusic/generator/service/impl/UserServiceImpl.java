package com.snow.fvmusic.generator.service.impl;

import com.snow.fvmusic.generator.entities.User;
import com.snow.fvmusic.generator.mapper.UserMapper;
import com.snow.fvmusic.generator.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
