package com.zch.service.impl;

import com.zch.enums.OrderStatusEnum;
import com.zch.enums.YseOrNo;
import com.zch.mapper.OrderItemsMapper;
import com.zch.mapper.OrderMapperCustom;
import com.zch.mapper.OrderStatusMapper;
import com.zch.mapper.OrdersMapper;
import com.zch.pojo.OrderItems;
import com.zch.pojo.OrderStatus;
import com.zch.pojo.Orders;
import com.zch.pojo.UserAddress;
import com.zch.pojo.bo.SubmitOrderBO;
import com.zch.pojo.vo.MerchantOrdersVO;
import com.zch.pojo.vo.OrderVO;
import com.zch.pojo.vo.ShopcartVO;
import com.zch.service.ItemService;
import com.zch.service.OrderService;
import com.zch.service.UserAddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: zch
 * @create: 2020-10-20 21:05
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private OrderMapperCustom orderMapperCustom;

    @Autowired
    private Sid sid;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private ItemService itemService;


    /**
     * 创建订单
     *
     * @param submitOrderBO
     * @return
     */
    @Override
    public OrderVO create(SubmitOrderBO submitOrderBO) {
        String userId = submitOrderBO.getUserId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        String addressId = submitOrderBO.getAddressId();
        String leftMsg = submitOrderBO.getLeftMsg();
        Integer payMethod = submitOrderBO.getPayMethod();

        //包邮费设置为0
        Integer postAmount = 0;

        // 1. 新订单数据保存
        Orders orders = new Orders();
        String orderId = sid.nextShort();
        orders.setId(orderId);
        orders.setUserId(userId);
        //查询地址内容
        UserAddress userAddress = userAddressService.getUserAddressById(addressId);
        orders.setReceiverName(userAddress.getReceiver());
        orders.setReceiverMobile(userAddress.getMobile());
        orders.setReceiverAddress(userAddress.getProvince() + " " +
                userAddress.getCity() + " " +
                userAddress.getDistrict() + " " +
                userAddress.getDetail());
        orders.setPostAmount(postAmount);
        orders.setPayMethod(payMethod);
        orders.setLeftMsg(leftMsg);
        orders.setIsComment(YseOrNo.NO.type);
        orders.setIsDelete(YseOrNo.NO.type);
        orders.setCreatedTime(new Date());
        orders.setUpdatedTime(new Date());

        // 2. 获取购物车中商品信息 保存订单商品
        List<ShopcartVO> itemList = itemService.queryItemsBySpecIds(itemSpecIds);
        // 统计所有商品的价格之和
        Integer totalAmount = 0, realPayAmount = 0;
        for (ShopcartVO item : itemList) {
            // TODO 整合redis后 购物车的数量从 redis 中获取
            Integer buyCounts = 1;
            totalAmount += item.getPriceNormal() * buyCounts;
            realPayAmount += item.getPriceDiscount() * buyCounts;
            // 保存订单商品
            OrderItems subOrderItems = new OrderItems();
            subOrderItems.setId(sid.nextShort());
            subOrderItems.setOrderId(orderId);
            subOrderItems.setItemId(item.getItemId());
            subOrderItems.setItemImg(item.getItemImgUrl());
            subOrderItems.setItemName(item.getItemName());
            subOrderItems.setItemSpecId(item.getSpecId());
            subOrderItems.setItemSpecName(item.getItemName());
            subOrderItems.setPrice(item.getPriceDiscount());
            subOrderItems.setBuyCounts(buyCounts);
            orderItemsMapper.insert(subOrderItems);

            //扣除库存
            itemService.decreaseItemSpecStock(item.getSpecId(), buyCounts);
        }

        orders.setTotalAmount(totalAmount);
        orders.setRealPayAmount(realPayAmount);
        ordersMapper.insert(orders);

        // 3. 保存订单状态
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        orderStatus.setCreatedTime(new Date());

        // 4. 构建商户订单，用于传给支付中心
        MerchantOrdersVO merchantOrdersVO = new MerchantOrdersVO();
        merchantOrdersVO.setMerchantOrderId(orderId);
        merchantOrdersVO.setMerchantUserId(userId);
        merchantOrdersVO.setPayMethod(payMethod);
        merchantOrdersVO.setAmount(realPayAmount + postAmount);

        OrderVO orderVO = new OrderVO();
        orderVO.setMerchantOrdersVO(merchantOrdersVO);
        orderVO.setOrderId(orderId);

        orderStatusMapper.insert(orderStatus);
        return orderVO;
    }

    /**
     * 关闭已超时但未支付的订单
     */
    @Override
    public int closeOvertimeOrders() {
       return orderMapperCustom.closeOvertimeOrder();
    }

    /**
     * 修改订单状态
     *
     * @param orderId
     * @param orderStatus
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {})
    @Override
    public int updateOrderStatus(String orderId, Integer orderStatus) {
        OrderStatus order = new OrderStatus();
        order.setOrderId(orderId);
        order.setOrderStatus(orderStatus);
        order.setPayTime(new Date());

        return orderStatusMapper.updateByPrimaryKeySelective(order);
    }
}
