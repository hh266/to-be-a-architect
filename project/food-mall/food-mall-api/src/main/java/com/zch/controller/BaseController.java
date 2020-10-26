package com.zch.controller;

/**
 * @author zch
 * @date 2020/10/15 15:41
 */
public class BaseController {
    public static final String FOODIE_SHOPCART = "shopcart";


    public static final Integer COMMON_PAGE_SIZE = 10;
    public static final Integer ITEM_PAGE_SIZE = 20;

    /**
     * 微信支付成功 -> 支付中心 -> 天天吃货平台
     * 回调中心的url
     */
    String payReturnUrl = "http://localhost:8088/order/notifyMerchantOrderPaid";
}
