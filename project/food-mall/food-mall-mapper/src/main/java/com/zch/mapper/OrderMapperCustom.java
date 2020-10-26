package com.zch.mapper;

/**
 * 订单的自定义mapper
 *
 * @author zch
 * @date 2020/10/26 17:32
 */
public interface OrderMapperCustom {

    /**
     * 关闭已超时但未支付的订单
     *
     * @return
     */
    public int closeOvertimeOrder();
}
