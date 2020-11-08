package com.zch.controller.center;

import cn.hutool.core.util.StrUtil;
import com.zch.center.service.MyCommentsService;
import com.zch.controller.BaseController;
import com.zch.pojo.bo.center.OrderItemsCommentBO;
import com.zch.result.CommonResult;
import com.zch.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
public class MyCommentsController extends BaseController {

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

        myCommentsService.saveCommentList(userId, orderId, commentBOList);

        return CommonResult.success();
    }

    @ApiOperation(value = "查询我的评价", notes = "查询我的评价", httpMethod = "POST")
    @PostMapping("/query")
    public CommonResult query(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "page", value = "查询下一页的第几页", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页的每一页显示的条数", required = false)
            @RequestParam Integer pageSize) {

        if (StrUtil.isBlank(userId)) {
            return CommonResult.validateFailed();
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }

        PagedGridResult grid = myCommentsService.queryMyComments(userId,
                page,
                pageSize);

        return CommonResult.success(grid);
    }
}
