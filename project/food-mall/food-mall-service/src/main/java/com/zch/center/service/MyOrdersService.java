package com.zch.center.service;

import com.zch.utils.PagedGridResult;

/**
 * 个人之心订单管理
 *
 * @author zch
 * @date 2020/11/4 16:31
 */
public interface MyOrdersService {

    /**
     * 查询我的订单列表
     *
     * @param userId
     * @param orderStatus
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult queryMyOrders(String userId,
                                         Integer orderStatus,
                                         Integer page,
                                         Integer pageSize);


    /**
     * 用户修改订单状态
     *
     * @param userId
     * @param OrderId
     * @param status
     * @return
     */
    public Integer updateOrderStatus(String userId, String OrderId, int status);
}
