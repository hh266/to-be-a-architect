package com.zch.center.service;

import com.zch.pojo.OrderItems;
import com.zch.pojo.bo.center.OrderItemsCommentBO;

import java.util.List;

/**
 * @author: zch
 * @create: 2020-11-05 21:24
 */
public interface MyCommentsService {

    /**
     * 根据待评论订单id 查询关联商品
     *
     * @param userId
     * @param orderId
     * @return
     */
    public List<OrderItems> queryPendingComment(String userId, String orderId);


    /**
     * 保存商品评论
     *
     * @param userId
     * @param orderId
     * @param commentBOList
     */
    public void saveCommentList(String userId, String orderId, List<OrderItemsCommentBO> commentBOList);
}
