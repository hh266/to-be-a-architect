package com.zch.service.impl;

import com.zch.enums.YseOrNo;
import com.zch.mapper.OrdersMapper;
import com.zch.pojo.Orders;
import com.zch.pojo.UserAddress;
import com.zch.pojo.bo.SubmitOrderBO;
import com.zch.service.OrderService;
import com.zch.service.UserAddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    private Sid sid;

    @Autowired
    private UserAddressService userAddressService;


    /**
     * 创建订单
     *
     * @param submitOrderBO
     * @return
     */
    @Override
    public int create(SubmitOrderBO submitOrderBO) {
        String userId = submitOrderBO.getUserId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        String addressId = submitOrderBO.getUserId();
        String leftMsg = submitOrderBO.getLeftMsg();
        Integer payMethod = submitOrderBO.getPayMethod();

        //包邮费设置为0
        Integer postAmount = 0;


        // 1. 新订单数据保存
        Orders orders = new Orders();
        orders.setId(sid.nextShort());
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
        //orders.setTotalAmount();
        //orders.setPayMethod();
        orders.setPayMethod(payMethod);
        orders.setLeftMsg(leftMsg);
        orders.setIsComment(YseOrNo.NO.type);
        orders.setIsDelete(YseOrNo.NO.type);
        orders.setCreatedTime(new Date());
        orders.setUpdatedTime(new Date());

        // 2. 根据循环userId 保存订单商品


        // 3.

        return 0;
    }
}
