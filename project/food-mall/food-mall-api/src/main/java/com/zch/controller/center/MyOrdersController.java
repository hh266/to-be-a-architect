package com.zch.controller.center;

import cn.hutool.core.util.StrUtil;
import com.zch.center.service.MyOrdersService;
import com.zch.controller.BaseController;
import com.zch.enums.OrderStatusEnum;
import com.zch.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "用户中心我的订单", tags = {"用户中心我的订单相关接口"})
@RestController
@RequestMapping("myorders")
public class MyOrdersController extends BaseController {

    @Autowired
    private MyOrdersService myOrdersService;


    @ApiOperation(value = "查询订单列表", notes = "查询订单列表", httpMethod = "POST")
    @PostMapping("/query")
    public CommonResult query(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderStatus", value = "订单状态", required = false)
            @RequestParam Integer orderStatus,
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

        return CommonResult.success(myOrdersService.queryMyOrders(userId,
                orderStatus,
                page,
                pageSize));
    }

    @ApiOperation(value = "确认收货", notes = "用户确认收货", httpMethod = "POST")
    @PostMapping("/confirmReceive")
    public CommonResult confirmReceive(@RequestParam String userId,
                                       @RequestParam String orderId) {
        myOrdersService.updateOrderStatus(userId, orderId, OrderStatusEnum.SUCCESS.type);
       return  CommonResult.success();
    }

    @ApiOperation(value = "删除订单", notes = "用户删除订单", httpMethod = "POST")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam String userId,
                                       @RequestParam String orderId) {
        myOrdersService.deleteOrder(userId, orderId);
        return  CommonResult.success();
    }




}
