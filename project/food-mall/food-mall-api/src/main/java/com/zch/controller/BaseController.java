package com.zch.controller;

import java.io.File;

/**
 * @author zch
 * @date 2020/10/15 15:41
 */
public class BaseController {
    public static final String FOODIE_SHOPCART = "shopcart";


    public static final Integer COMMON_PAGE_SIZE = 10;
    public static final Integer ITEM_PAGE_SIZE = 20;

    /**
     * 用户上传头像的位置
     */
    public static final String IMAGE_USER_FACE_LOCATION = File.separator + "uploadFile" +
            File.separator + "imooc" +
            File.separator + "images" +
            File.separator + "foodie" +
            File.separator + "faces";

    /**
     * imooc 支付中心账号密码
     */
    String imoocUserId = "7363907-1297293405";
    String imoocPassword = "t430-89tj-509j-ut09";

    /**
     * 支付中心的调用地址
     */
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";


    /**
     * 微信支付成功 -> 支付中心 -> 天天吃货平台
     * 回调中心的url
     */
    String payReturnUrl = "http://3p3jn3.natappfree.cc/orders/notifyMerchantOrderPaid";

}
