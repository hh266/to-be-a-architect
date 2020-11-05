package com.zch.controller.center;

import com.zch.center.service.MyCommentsService;
import com.zch.pojo.bo.center.OrderItemsCommentBO;
import com.zch.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 个人中心评论模块
 * @author: zch
 * @create: 2020-11-05 21:41
 */
@Api(value = "用户中心评论模块", tags = {"用户中心评论模块相关接口"})
@RestController
@RequestMapping("/mycomments")
public class MyCommentsController {

    @Autowired
    private MyCommentsService myCommentsService;

    @ApiOperation(value = "获取订单的关联商品", notes = "获取待评论订单的关联商品", httpMethod = "POST")
    @PostMapping("/pending")
    public CommonResult pending(@RequestParam String userId, @RequestParam String orderId) {
        return CommonResult.success(myCommentsService.queryPendingComment(userId, orderId));
    }

    @ApiOperation(value = "获取订单的关联商品", notes = "获取待评论订单的关联商品", httpMethod = "POST")
    @PostMapping("/saveList")
    public CommonResult saveList(@RequestParam String userId,
                                 @RequestParam String orderId,
                                 @RequestBody @Validated List<OrderItemsCommentBO> commentBOList) {

        System.out.println(commentBOList);

        return CommonResult.success();
    }

}
