package com.zch.controller;

import cn.hutool.core.util.StrUtil;
import com.zch.enums.YseOrNo;
import com.zch.pojo.Carousel;
import com.zch.pojo.Category;
import com.zch.pojo.vo.CategoryVO;
import com.zch.result.CommonResult;
import com.zch.service.CarouselService;
import com.zch.service.CategoryService;
import com.zch.utils.JsonUtils;
import com.zch.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private RedisOperator redisOperator;

    /**
     * 获取轮播图
     *
     * @return
     */
    @ApiOperation(value = "获取轮播图", notes = "获取轮播图", httpMethod = "GET")
    @GetMapping("/carousel")
    public CommonResult carousel() {

        List<Carousel> carouselList = new ArrayList<>();
        String carouselStr = redisOperator.get("carousel");

        if (StrUtil.isBlank(carouselStr)) {
            carouselList = carouselService.queryAll(YseOrNo.YES.type);
            redisOperator.set("carousel", JsonUtils.objectToJson(carouselList));
        } else {
            carouselList = JsonUtils.jsonToList(carouselStr, Carousel.class);
        }


        return CommonResult.success(carouselList);
    }


    /**
     * 获取 一级分类
     *
     * @return
     */
    @ApiOperation(value = "查询所有商品一级分类", notes = "查询所有商品一级分类", httpMethod = "GET")
    @GetMapping("/cats")
    public CommonResult cats() {

        List<Category> categoryList = new ArrayList<>();
        String categoryStr = redisOperator.get("category");
        if (StrUtil.isBlank(categoryStr)) {
            categoryList = categoryService.queryAllRootLevelCat();
            redisOperator.set("category", JsonUtils.objectToJson(categoryList));
        } else {
            categoryList = JsonUtils.jsonToList(categoryStr, Category.class);
        }

        return CommonResult.success(categoryList);
    }


    /**
     * 获取 商品子分类
     *
     * @return
     */
    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public CommonResult subCat(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            CommonResult.validateFailed();
        }

        List<CategoryVO> categoryVOList = new ArrayList<>();
        String categoryStr = redisOperator.get("category" + rootCatId);

        if (StrUtil.isBlank(categoryStr)) {
            categoryVOList = categoryService.getSubCatList(rootCatId);
            redisOperator.set("category" + rootCatId, JsonUtils.objectToJson(categoryVOList));
        } else {
            categoryVOList = JsonUtils.jsonToList(categoryStr, CategoryVO.class);
        }

        return CommonResult.success(categoryVOList);
    }

    /**
     * 获取 一级分类下6条最新商品数
     *
     * @return
     */
    @ApiOperation(value = "一级分类下6条最新商品数", notes = "一级分类下6条最新商品数", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public CommonResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            CommonResult.validateFailed();
        }
        return CommonResult.success(categoryService.getSixNewItemsLazy(rootCatId));
    }

}
