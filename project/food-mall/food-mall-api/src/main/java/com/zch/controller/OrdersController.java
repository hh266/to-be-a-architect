package com.zch.controller;

import com.zch.Utils.CookieUtils;
import com.zch.Utils.JsonUtils;
import com.zch.enums.OrderStatusEnum;
import com.zch.pojo.bo.SubmitOrderBO;
import com.zch.pojo.vo.MerchantOrdersVO;
import com.zch.pojo.vo.OrderVO;
import com.zch.result.CommonResult;
import com.zch.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "添加订单", notes = "添加订单", httpMethod = "POST")
    @PostMapping("/create")
    public CommonResult create(@Validated @RequestBody SubmitOrderBO submitOrderBO,
                               HttpServletRequest request,
                               HttpServletResponse response){
        // 1. 创建购物车
        OrderVO orderVO = service.create(submitOrderBO);
        String orderId = orderVO.getOrderId();


        // 2. 移除购物车中的订单
        // TODO 移除 redis 中购物车的数据，并且同步到 前端 Cookie
        // 移除cookie中的数据
        // CookieUtils.setCookie(request, response, FOODIE_SHOPCART,  "", true);

        // 3.向购物中心发送当前订单，用于保存支付中心的订单数据
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(payReturnUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("imoocUserId",imoocUserId);
        headers.add("password",imoocPassword);

        HttpEntity<MerchantOrdersVO> entity = new HttpEntity<>(merchantOrdersVO, headers);

       ResponseEntity<CommonResult> responseEntity = restTemplate.postForEntity(paymentUrl, entity, CommonResult.class);

        responseEntity.getBody();

        return CommonResult.success(orderId,"ok");
    }

    @PostMapping("/notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId){

        service.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);

        return HttpStatus.OK.value();
    }

}
