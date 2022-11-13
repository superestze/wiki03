package com.zhengqi.wiki03.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.rmi.registry.Registry;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        //        请求的路径地址
        registry.addMapping("/**")
                // 允许来源,
                .allowedOriginPatterns("*")
                .allowedHeaders(CorsConfiguration.ALL)
                // GET POST 等请求方式
                .allowedMethods(CorsConfiguration.ALL)
                // 凭证 例如token
                .allowCredentials(true)
                // 1小时之内不需要检验
                .maxAge(3600);
    }

}
