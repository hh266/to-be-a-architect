package com.zch.service;

import com.zch.pojo.OrderStatus;
import com.zch.pojo.bo.SubmitOrderBO;
import com.zch.pojo.vo.OrderVO;

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
    public OrderVO create(SubmitOrderBO submitOrderBO);


    /**
     * 修改订单状态
     *
     * @param orderId
     * @param orderStatus
     * @return
     */
    public int updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 关闭已超时但未支付的订单
     *
     * @return
     */
    public int closeOvertimeOrders();

    /**
     * 查询订单信息
     *
     * @param orderId
     * @return
     */
    public OrderStatus queryOrderStatusInfo(String orderId);
}
