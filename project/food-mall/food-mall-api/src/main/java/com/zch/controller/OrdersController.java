package com.zch.controller;

import com.zch.pojo.bo.SubmitOrderBO;
import com.zch.result.CommonResult;
import com.zch.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: orders
 * @author: zch
 * @create: 2020-10-20 20:30
 */
@Api(value = "订单API", tags = {"订单相关API"})
@RestController
@RequestMapping("/orders")
public class OrdersController extends BaseController {

    @Autowired
    private OrderService service;

    @ApiOperation(value = "添加订单", notes = "添加订单", httpMethod = "POST")
    @PostMapping("/create")
    public CommonResult create(@Validated @RequestBody SubmitOrderBO submitOrderBO){
        service.create(submitOrderBO);
        return CommonResult.success();
    }
}
