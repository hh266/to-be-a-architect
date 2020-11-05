package com.zch.center.service.impl;

import com.zch.center.service.MyCommentsService;
import com.zch.enums.YseOrNo;
import com.zch.exception.Asserts;
import com.zch.mapper.OrderItemsMapper;
import com.zch.mapper.OrdersMapper;
import com.zch.pojo.OrderItems;
import com.zch.pojo.Orders;
import com.zch.pojo.bo.center.OrderItemsCommentBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @description:
 * @author: zch
 * @create: 2020-11-05 21:25
 */
@Service
public class MyCommentsServiceImpl extends BaseService implements MyCommentsService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    /**
     * 根据待评论订单id 查询关联商品
     *
     * @param userId
     * @param orderId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<OrderItems> queryPendingComment(String userId, String orderId) {
        // 1. 检查商品状态
        checkOrderStatus(userId, orderId);

        // 2. 查找订单关联的商品
        OrderItems query = new OrderItems();
        query.setOrderId(orderId);
        return orderItemsMapper.select(query);
    }

    /**
     * 保存商品评论
     *
     * @param userId
     * @param orderId
     * @param commentBOList
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void saveCommentList(String userId, String orderId, List<OrderItemsCommentBO> commentBOList) {
        // 1. 检查商品状态
        checkOrderStatus(userId, orderId);

        // 2. 保存评价

        // 3. 修改订单表为已评价

        //4. 修改 订单状态表 评论时间
    }

    /**
     * 检查商品状态
     *
     * @param userId
     * @param orderId
     * @return
     */
    public Orders checkOrderStatus(String userId, String orderId){
        // 1. 查看该订单是否存在
        Example example = new Example(Orders.class);
        Example.Criteria Criteria = example.createCriteria();
        Criteria.andEqualTo("id", orderId);
        Criteria.andEqualTo("userId", userId);
        Orders orders = ordersMapper.selectOneByExample(example);
        if(orders == null){
            Asserts.fail("该订单不存在！");
        }

        // 2. 查看该订单是否已经评论
        if(orders.getIsComment() == 1){
            Asserts.fail("该订单已评论！");
        }

        return orders;
    }


}
