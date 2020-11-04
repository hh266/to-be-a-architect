package com.zch.center.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zch.center.service.MyOrdersService;
import com.zch.enums.OrderStatusEnum;
import com.zch.mapper.OrderStatusMapper;
import com.zch.mapper.OrdersMapper;
import com.zch.mapper.OrdersMapperCustom;
import com.zch.pojo.OrderStatus;
import com.zch.pojo.Orders;
import com.zch.pojo.vo.MyOrdersVO;
import com.zch.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人之心订单管理
 * @author zch
 * @date 2020/11/4 16:32
 */
@Service
public class MyOrdersServiceImpl implements MyOrdersService {

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
     * @param OrderId
     * @param status
     * @return
     */
    @Override
    public Integer updateOrderStatus(String userId, String OrderId, int status) {
        // 0. 判断订单是否属于用户
        Orders checkOrder = new Orders();
        checkOrder.setId(OrderId);
        checkOrder.setUserId(userId);

        Orders checkOrderResult = ordersMapper.selectOne(checkOrder);
        if(checkOrderResult == null){
            return 0;
        }

        // 1. 根据不同的状态去修改
        // 确认收货 30 -> 40
        // 关闭交易 10 -> 50
        if(status == OrderStatusEnum.WAIT_PAY.type){

        }else if(status == OrderStatusEnum.WAIT_RECEIVE.type){

        }


        return null;
    }


    public PagedGridResult setterPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
