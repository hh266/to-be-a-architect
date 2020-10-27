package com.zch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @description 跨域配置
 * @author zch
 * @date 2020/10/9 11:55
 */
@Configuration
public class CorsConfig {

    public CorsConfig(){

    }

    @Bean
    public CorsFilter corsFilter(){
        // 1. 添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        //config.addAllowedOrigin("http://localhost:8080");

        // 设置是否发送 cookie 信息
        config.setAllowCredentials(true);

        // 设置允许的请求方式
        config.addAllowedMethod("*");

        // 设置允许的 header
        config.addAllowedHeader("*");

        // 2. 为 url 添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**",config);

        // 3. 返回重新定义好的 corsSource
        return new CorsFilter(corsSource);
    }
}
