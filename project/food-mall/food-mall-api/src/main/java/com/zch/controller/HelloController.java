package com.zch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zch
 * @date 2020/9/28 9:59
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public Object hello(){
        return "hello word!";
    }
}
