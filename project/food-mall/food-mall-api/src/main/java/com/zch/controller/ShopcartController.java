package com.zch.controller;

import cn.hutool.core.util.StrUtil;
import com.zch.pojo.bo.ShopcartBO;
import com.zch.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 购物车Api
 * @author zch
 * @date 2020/10/19 14:39
 */
@Api(value = "购物车API", tags = {"购物车相关的API"})
@RestController
@RequestMapping("/shopcart")
public class ShopcartController {

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public CommonResult add(
            @RequestParam String userId,
            @RequestBody ShopcartBO shopcartBO,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        if(StrUtil.isBlank(userId)){
            return CommonResult.validateFailed();
        }

        // TODO 前端用户在登录的情况下，添加商品到购物车, 后端会同步购物车到 redis 缓存

        return CommonResult.success();
    }

    @ApiOperation(value = "删除购物车中的商品", notes = "删除购物车中的商品", httpMethod = "POST")
    @PostMapping("/del")
    public CommonResult del(
            @RequestParam String userId,
            @RequestBody String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        if(StrUtil.isBlank(userId) || StrUtil.isBlank(itemSpecId)){
            return CommonResult.validateFailed();
        }

        // TODO 同步数据到redis

        return CommonResult.success();
    }
}
