package com.zch.controller;

import cn.hutool.core.util.StrUtil;
import com.zch.pojo.bo.ShopcartBO;
import com.zch.result.CommonResult;
import com.zch.utils.JsonUtils;
import com.zch.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车Api
 *
 * @author zch
 * @date 2020/10/19 14:39
 */
@Api(value = "购物车API", tags = {"购物车相关的API"})
@RestController
@RequestMapping("/shopcart")
public class ShopcartController extends BaseController{

    @Autowired
    private RedisOperator redisOperator;

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public CommonResult add(
            @RequestParam String userId,
            @RequestBody ShopcartBO shopcartBO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        if (StrUtil.isBlank(userId)) {
            return CommonResult.validateFailed();
        }

        // 前端用户在登录的情况下，添加商品到购物车, 后端会同步购物车到 redis 缓存
        List<ShopcartBO> shopcartBOList = new ArrayList<>();
        String shopcatStr = redisOperator.get(FOODIE_SHOPCART + "_" + userId);
        if (StrUtil.isBlank(shopcatStr)) {
            shopcartBOList.add(shopcartBO);
        } else {
            Boolean isHaving = false;
            shopcartBOList = JsonUtils.jsonToList(shopcatStr, ShopcartBO.class);
            for (ShopcartBO bo : shopcartBOList) {
                if (StrUtil.equals(bo.getSpecId(), shopcartBO.getSpecId())) {
                    isHaving = true;
                    bo.setBuyCounts(bo.getBuyCounts() + shopcartBO.getBuyCounts());
                }
            }
            if(!isHaving){
                shopcartBOList.add(shopcartBO);
            }
        }
        redisOperator.set(FOODIE_SHOPCART + "_" + userId, JsonUtils.objectToJson(shopcartBOList));


        return CommonResult.success();
    }

    @ApiOperation(value = "删除购物车中的商品", notes = "删除购物车中的商品", httpMethod = "POST")
    @PostMapping("/del")
    public CommonResult del(
            @RequestParam String userId,
            @RequestParam String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        if (StrUtil.isBlank(userId) || StrUtil.isBlank(itemSpecId)) {
            return CommonResult.validateFailed();
        }

        // 同步数据到redis
        List<ShopcartBO> shopcartBOList = new ArrayList<>();
        String shopcatStr = redisOperator.get(FOODIE_SHOPCART + "_" + userId);
        if (StrUtil.isNotBlank(shopcatStr)) {
            shopcartBOList = JsonUtils.jsonToList(shopcatStr, ShopcartBO.class);
            for (ShopcartBO bo : shopcartBOList) {
                if (StrUtil.equals(bo.getSpecId(), itemSpecId)) {
                    shopcartBOList.remove(bo);
                    break;
                }
            }
            redisOperator.set(FOODIE_SHOPCART + "_" + userId, JsonUtils.objectToJson(shopcartBOList));
        }

        return CommonResult.success();
    }
}
