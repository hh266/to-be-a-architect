package com.zch.center.service.impl;

import com.github.pagehelper.PageHelper;
import com.zch.center.service.MyCommentsService;
import com.zch.enums.YseOrNo;
import com.zch.exception.Asserts;
import com.zch.mapper.ItemsCommentsMapperCustom;
import com.zch.mapper.OrderItemsMapper;
import com.zch.mapper.OrderStatusMapper;
import com.zch.mapper.OrdersMapper;
import com.zch.pojo.OrderItems;
import com.zch.pojo.OrderStatus;
import com.zch.pojo.Orders;
import com.zch.pojo.bo.center.OrderItemsCommentBO;
import com.zch.pojo.vo.MyCommentVO;
import com.zch.utils.PagedGridResult;
import org.n3r.idworker.Sid;
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
 * @description:
 * @author: zch
 * @create: 2020-11-05 21:25
 */
@Service
public class MyCommentsServiceImpl extends BaseService implements MyCommentsService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private ItemsCommentsMapperCustom itemsCommentsMapperCustom;

    @Autowired
    private Sid sid;

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
     * @param commentList
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void saveCommentList(String userId, String orderId, List<OrderItemsCommentBO> commentList) {
        // 1. 检查商品状态
        checkOrderStatus(userId, orderId);

        // 2. 保存评价
        for (OrderItemsCommentBO bo : commentList) {
            bo.setCommentId(sid.nextShort());
        }
        Map<String, Object> queryMap = new HashMap<>(2);
        queryMap.put("userId", userId);
        queryMap.put("commentList", commentList);
        itemsCommentsMapperCustom.saveComments(queryMap);

        // 3. 修改订单表为已评价
        Orders order = new Orders();
        order.setId(orderId);
        order.setIsComment(YseOrNo.YES.type);
        ordersMapper.updateByPrimaryKeySelective(order);

        //4. 修改 订单状态表 评论时间
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCommentTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public PagedGridResult queryMyComments(String userId,
                                           Integer page,
                                           Integer pageSize) {

        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", userId);

        PageHelper.startPage(page, pageSize);
        List<MyCommentVO> list = itemsCommentsMapperCustom.queryMyComments(map);

        return setterPagedGrid(list, page);
    }


    /**
     * 检查商品状态
     *
     * @param userId
     * @param orderId
     * @return
     */
    public Orders checkOrderStatus(String userId, String orderId) {
        // 1. 查看该订单是否存在
        Example example = new Example(Orders.class);
        Example.Criteria Criteria = example.createCriteria();
        Criteria.andEqualTo("id", orderId);
        Criteria.andEqualTo("userId", userId);
        Orders orders = ordersMapper.selectOneByExample(example);
        if (orders == null) {
            Asserts.fail("该订单不存在！");
        }

        // 2. 查看该订单是否已经评论
        if (orders.getIsComment() == 1) {
            Asserts.fail("该订单已评论！");
        }

        return orders;
    }

}
