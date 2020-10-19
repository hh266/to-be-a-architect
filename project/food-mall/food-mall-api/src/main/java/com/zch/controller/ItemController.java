package com.zch.controller;

import cn.hutool.core.util.StrUtil;
import com.zch.enums.ItemCommentLevel;
import com.zch.pojo.vo.ItemCommentLevelCountsVO;
import com.zch.pojo.vo.ItemVO;
import com.zch.result.CommonResult;
import com.zch.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 商品详情页
 * @author: zch
 * @create: 2020-10-13 22:51
 */
@Api(value = "商品详情", tags = {"用于获取商品相关的接口"})
@RestController
@RequestMapping("/items")
public class ItemController extends BaseController{

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "获取商品详情", notes = "获取商品详情，包含图片、规格、参数。", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public CommonResult info(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @PathVariable String itemId){

        if(StrUtil.isBlank(itemId)){
            CommonResult.validateFailed("商品id不能为空");
        }

        ItemVO itemVO = new ItemVO();

        itemVO.setItem(itemService.getItemById(itemId));
        itemVO.setItemImgList(itemService.getItemImages(itemId));
        itemVO.setItemSpecList(itemService.getItemItemsSpecs(itemId));
        itemVO.setItemParams(itemService.getItemParam(itemId));

        return CommonResult.success(itemVO);
    }


    @ApiOperation(value = "获取商品评论数", notes = "获取商品不同级别的评论数", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public CommonResult commentLevel(
            @ApiParam(name = "itemId", value = "商品id", required = true) String itemId){

        if(StrUtil.isBlank(itemId)){
            CommonResult.validateFailed("商品id不能为空");
        }

        ItemCommentLevelCountsVO countsVO = new ItemCommentLevelCountsVO(
                itemService.getItemCommentLevelCounts(itemId, ItemCommentLevel.goodLevel.type),
                itemService.getItemCommentLevelCounts(itemId, ItemCommentLevel.normalLevel.type),
                itemService.getItemCommentLevelCounts(itemId, ItemCommentLevel.badLevel.type)
        );

        return CommonResult.success(countsVO);
    }

    @ApiOperation(value = "获取商品评论列表", notes = "获取商品列表", httpMethod = "GET")
    @GetMapping("/comments")
    public CommonResult comments(@ApiParam(name = "itemId", value = "商品id", required = true) String itemId,
                                 @ApiParam(name = "level", value = "评论等级", required = false) Integer level,
                                 @ApiParam(name = "page", value = "第几页", required = false) Integer page,
                                 @ApiParam(name = "pageSize", value = "每页数量", required = false) Integer pageSize){

        if(StrUtil.isBlank(itemId)){
            CommonResult.validateFailed("商品id不能为空");
        }

        if(page == null){
            page = 1;
        }

        if(pageSize == null){
            pageSize = COMMON_PAGE_SIZE;
        }

        return CommonResult.success(itemService.getItemCommentList(itemId, level, page, pageSize));
    }

    @ApiOperation(value = "搜索商品", notes = "通过关键词搜索商品", httpMethod = "GET")
    @GetMapping("/search")
    public CommonResult search(@ApiParam(name = "keywords", value = "商品id", required = false) String keywords,
                                 @ApiParam(name = "sort", value = "评论等级", required = false) String sort,
                                 @ApiParam(name = "page", value = "第几页", required = false) Integer page,
                                 @ApiParam(name = "pageSize", value = "每页数量", required = false) Integer pageSize){

        if(page == null){
            page = 1;
        }

        if(pageSize == null){
            pageSize = ITEM_PAGE_SIZE;
        }

        return CommonResult.success(itemService.getItemsByKeywords(keywords, sort, page, pageSize));
    }

    @ApiOperation(value = "分类商品", notes = "三级分类商品列表", httpMethod = "GET")
    @GetMapping("/catItems")
    public CommonResult catItems(@ApiParam(name = "catId", value = "商品id", required = true) Integer catId,
                               @ApiParam(name = "sort", value = "评论等级", required = false) String sort,
                               @ApiParam(name = "page", value = "第几页", required = false) Integer page,
                               @ApiParam(name = "pageSize", value = "每页数量", required = false) Integer pageSize){

        if(catId == null){
            CommonResult.validateFailed();
        }

        if(page == null){
            page = 1;
        }

        if(pageSize == null){
            pageSize = ITEM_PAGE_SIZE;
        }

        return CommonResult.success(itemService.getItemsByThirdCat(catId, sort, page, pageSize));
    }

    @ApiOperation(value = "通过规格 Ids 获取商品信息", notes = "通过规格 Ids 获取最新的商品信息", httpMethod = "GET")
    @GetMapping("/refresh")
    public CommonResult refresh(@ApiParam(name = "商品规格 Ids", value = "商品规格 Ids", required = true, example = "1001,1002,1005")
                                    @RequestParam String itemSpecIds){

        if(StrUtil.isBlank(itemSpecIds)){
            return CommonResult.success();
        }

        return CommonResult.success(itemService.queryItemsBySpecIds(itemSpecIds));
    }

}
