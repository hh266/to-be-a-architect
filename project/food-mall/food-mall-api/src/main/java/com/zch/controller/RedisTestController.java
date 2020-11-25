package com.zch.controller;

import com.zch.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author zch
 * @date 2020/11/25 16:25
 */
@ApiIgnore
@RestController
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/set")
    public CommonResult set(String key, String value){
        redisTemplate.opsForValue().set(key, value);
        return CommonResult.success();
    }

    @GetMapping("/get")
    public CommonResult get(String key){
        return CommonResult.success(redisTemplate.opsForValue().get(key));
    }

    @GetMapping("/delete")
    public CommonResult delete(String key){
        return CommonResult.success(redisTemplate.delete(key));
    }
}
