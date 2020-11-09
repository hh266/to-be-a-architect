package com.zch;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 打包war [4] 增加war的启动类
 * @author: zch
 * @create: 2020-11-09 23:36
 */
public class WarStarterApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        //指向Application 这个 Springboot 启动类
        return builder.sources(Application.class);
    }
}
