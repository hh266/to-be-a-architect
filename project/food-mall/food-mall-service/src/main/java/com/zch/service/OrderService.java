package com.zch.service;

import com.zch.pojo.bo.SubmitOrderBO;

/**
 * @description: 订单 order service
 * @author: zch
 * @create: 2020-10-20 21:04
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param submitOrderBO
     * @return
     */
    public String create(SubmitOrderBO submitOrderBO);


    /**
     * 关闭已超时但未支付的订单
     *
     * @return
     */
    public int closeOvertimeOrders();
}
