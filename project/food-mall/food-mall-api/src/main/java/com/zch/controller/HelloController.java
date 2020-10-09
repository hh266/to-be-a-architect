package com.zch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author zch
 * @date 2020/9/28 9:59
 */
@ApiIgnore
@RestController
public class HelloController {

    final static Logger logger = LoggerFactory.getLogger(HelloController.class);


    @RequestMapping("/hello")
    public Object hello(){

        logger.info("hello info");

        logger.debug("hello debug");
        logger.warn("hello warn");
        logger.error("hello error");

        return "hello word!";
    }
}
