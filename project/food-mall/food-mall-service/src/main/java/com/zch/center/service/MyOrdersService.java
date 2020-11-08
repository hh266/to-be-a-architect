package com.zch.center.service;

import com.zch.pojo.vo.OrderStatusCountsVO;
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
     * @param orderId
     * @param status
     * @return
     */
    public void updateOrderStatus(String userId, String orderId, int status);

    /**
     * 用户删除订单
     *
     * @param userId
     * @param orderId
     * @return
     */
    public void deleteOrder(String userId, String orderId);

    /**
     * 查询用户订单数
     *
     * @param userId
     */
    public OrderStatusCountsVO getOrderStatusCounts(String userId);

    /**
     * 获得分页的订单动向
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult getOrdersTrend(String userId,
                                          Integer page,
                                          Integer pageSize);
}
