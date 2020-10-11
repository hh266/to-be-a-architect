package com.zch.controller;

import com.zch.enums.YseOrNo;
import com.zch.result.CommonResult;
import com.zch.service.CarouselService;
import com.zch.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页数据接口
 *
 * @author: zch
 * @create: 2020-10-11 12:05
 */
@Api(value = "首页", tags = {"首页展示的相关接口"})
@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;
    /**
     * 获取轮播图
     *
     * @return
     */
    @ApiOperation(value = "获取轮播图", notes = "获取轮播图", httpMethod = "GET")
    @GetMapping("/carousel")
    public CommonResult carousel() {
        return CommonResult.success(carouselService.queryAll(YseOrNo.YES.type));
    }


    /**
     * 获取 一级分类
     *
     * @return
     */
    @ApiOperation(value = "查询所有一级商品分类", notes = "查询所有一级商品分类", httpMethod = "GET")
    @GetMapping("/cats")
    public CommonResult cats() {
        return CommonResult.success(categoryService.queryAllRootLevelCat());
    }
}
