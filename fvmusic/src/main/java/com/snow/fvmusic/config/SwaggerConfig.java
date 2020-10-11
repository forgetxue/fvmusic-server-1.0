package com.snow.fvmusic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ：snow
 * @Date ：Created in 2020-07-09
 * @Description：
 * @Modified By：
 * @Version:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket (){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.snow.fvmusic.controller"))
                .build();
    }





    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .contact(new Contact("forgetxue","","forgetxue@163.com"))
                .description("FvMusic api")
                .title("FvMusic")
                .build();
    }


}
