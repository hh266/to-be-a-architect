package com.zch.controller;

import cn.hutool.core.util.StrUtil;
import com.zch.pojo.vo.ItemVO;
import com.zch.result.CommonResult;
import com.zch.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 商品详情页
 * @author: zch
 * @create: 2020-10-13 22:51
 */
@Api(value = "商品详情", tags = {"用于获取商品相关的接口"})
@RestController
@RequestMapping("/items")
public class ItemController {

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


}
