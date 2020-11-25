package com.zch.controller;

import com.zch.result.CommonResult;
import com.zch.utils.RedisOperator;
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
    private RedisOperator redisOperator;

    @GetMapping("/set")
    public CommonResult set(String key, String value){
        redisOperator.set(key, value);
        return CommonResult.success();
    }

    @GetMapping("/get")
    public CommonResult get(String key){
        return CommonResult.success(redisOperator.get(key));
    }

    @GetMapping("/delete")
    public CommonResult delete(String key){
        redisOperator.del(key);
        return CommonResult.success();
    }
}
