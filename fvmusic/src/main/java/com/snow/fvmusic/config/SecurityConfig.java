package com.snow.fvmusic.config;

import cn.hutool.json.JSONUtil;
import com.snow.fvmusic.bo.CommonResult;
import com.snow.fvmusic.bo.ResultCode;
import com.snow.fvmusic.component.JwtAuthenticationTokenFilter;
import com.snow.fvmusic.service.DynamicUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-20
 * @Description：
 * @Modified By：
 * @Version:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private DynamicUserService dynamicUserService;

    @Resource
    private AuthenticatedUrls authenticatedUrls;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> dynamicUserService.loadUserDetailsByUsername(username);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        registry.antMatchers(HttpMethod.OPTIONS).permitAll();
        for (String url : authenticatedUrls.getUrls()) {
            registry.antMatchers(url).authenticated();
        }
        registry.anyRequest()
                .permitAll()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        new AuthenticationEntryPoint() {
                            @Override
                            public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                                response.setHeader("Access-Control-Allow-Origin", "*");
                                response.setHeader("Cache-Control","no-cache");
                                response.setCharacterEncoding("UTF-8");
                                response.setContentType("application/json");
                                response.getWriter().println(JSONUtil.parse(CommonResult.failed(ResultCode.UN_AUTHORIZED, "您未登录或您的登录已经过期，请先登录！")));
                                response.getWriter().flush();
                            }
                        }
                )
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);


    }
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }
}
