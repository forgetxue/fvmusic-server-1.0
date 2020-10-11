package com.snow.fvmusic.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ：snow
 * @Date ：Created in 2020-07-11
 * @Description：
 * @Modified By：
 * @Version:
 */
@Configuration
public class MyBatisPlusConfig {
    @Bean
    PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
