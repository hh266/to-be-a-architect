package com.zch.center.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zch.center.service.MyOrdersService;
import com.zch.enums.OrderStatusEnum;
import com.zch.enums.YseOrNo;
import com.zch.exception.Asserts;
import com.zch.mapper.OrderStatusMapper;
import com.zch.mapper.OrdersMapper;
import com.zch.mapper.OrdersMapperCustom;
import com.zch.pojo.OrderStatus;
import com.zch.pojo.Orders;
import com.zch.pojo.Users;
import com.zch.pojo.vo.MyOrdersVO;
import com.zch.pojo.vo.OrderStatusCountsVO;
import com.zch.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人之心订单管理
 *
 * @author zch
 * @date 2020/11/4 16:32
 */
@Service
public class MyOrdersServiceImpl extends BaseService implements MyOrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private OrdersMapperCustom ordersMapperCustom;

    /**
     * 查询我的订单列表
     *
     * @param userId
     * @param orderStatus
     * @param page
     * @param pageSize
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        if (orderStatus != null) {
            map.put("orderStatus", orderStatus);
        }

        PageHelper.startPage(page, pageSize);

        List<MyOrdersVO> list = ordersMapperCustom.queryMyOrders(map);

        return setterPagedGrid(list, page);
    }


    /**
     * 修改订单状态
     *
     * @param orderId
     * @param status
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void updateOrderStatus(String userId, String orderId, int status) {
        // 0. 判断订单是否属于用户
        Orders checkOrder = new Orders();
        checkOrder.setId(orderId);
        checkOrder.setUserId(userId);

        Orders checkOrderResult = ordersMapper.selectOne(checkOrder);
        if (checkOrderResult == null) {
            Asserts.fail("订单不存在！");
        }

        // 1. 根据不同的状态去修改
        // 确认收货 30 -> 40
        // 关闭交易 10 -> 50
        Example example = new Example(OrderStatus.class);
        Example.Criteria Criteria = example.createCriteria();
        Criteria.andEqualTo("orderId", orderId);

        OrderStatus updateStatus = new OrderStatus();
        updateStatus.setOrderStatus(status);

        if (status == OrderStatusEnum.CLOSE.type) {
            // 关闭交易
            Criteria.andEqualTo("orderStatus", OrderStatusEnum.WAIT_PAY.type);
            updateStatus.setCloseTime(new Date());
        } else if (status == OrderStatusEnum.SUCCESS.type) {
            // 确认收货
            Criteria.andEqualTo("orderStatus", OrderStatusEnum.WAIT_RECEIVE.type);
            updateStatus.setSuccessTime(new Date());
        } else {
            Asserts.fail("订单状态异常！");
        }

        int result = orderStatusMapper.updateByExampleSelective(updateStatus, example);

        if (result != 1) {
            Asserts.fail("订单状态异常！");
        }
    }

    /**
     * 用户删除订单
     *
     * @param userId
     * @param orderId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void deleteOrder(String userId, String orderId) {
        Example example = new Example(Orders.class);
        Example.Criteria Criteria = example.createCriteria();
        Criteria.andEqualTo("id", orderId);
        Criteria.andEqualTo("userId", userId);
        Criteria.andEqualTo("isDelete", YseOrNo.NO.type);

        Orders updateOrders = new Orders();
        updateOrders.setIsDelete(YseOrNo.YES.type);
        updateOrders.setUpdatedTime(new Date());

        int result = ordersMapper.updateByExampleSelective(updateOrders, example);
        if (result != 1) {
            Asserts.fail("订单状态异常！删除失败");
        }
    }

    /**
     * 获取不同状态的订单数量
     *
     * @param userId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public OrderStatusCountsVO getOrderStatusCounts(String userId) {

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        map.put("orderStatus", OrderStatusEnum.WAIT_PAY.type);
        int waitPayCounts = ordersMapperCustom.getMyOrderStatusCounts(map);

        map.put("orderStatus", OrderStatusEnum.WAIT_DELIVER.type);
        int waitDeliverCounts = ordersMapperCustom.getMyOrderStatusCounts(map);

        map.put("orderStatus", OrderStatusEnum.WAIT_RECEIVE.type);
        int waitReceiveCounts = ordersMapperCustom.getMyOrderStatusCounts(map);

        map.put("orderStatus", OrderStatusEnum.SUCCESS.type);
        map.put("isComment", YseOrNo.NO.type);
        int waitCommentCounts = ordersMapperCustom.getMyOrderStatusCounts(map);

        OrderStatusCountsVO countsVO = new OrderStatusCountsVO(waitPayCounts,
                waitDeliverCounts,
                waitReceiveCounts,
                waitCommentCounts);
        return countsVO;
    }

    /**
     * 获得分页的订单动向
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult getOrdersTrend(String userId, Integer page, Integer pageSize) {

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        PageHelper.startPage(page, pageSize);
        List<OrderStatus> list = ordersMapperCustom.getMyOrderTrend(map);

        return setterPagedGrid(list, page);
    }
}
