package com.zch.mapper;

import com.zch.pojo.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单的自定义mapper
 *
 * @author zch
 * @date 2020/10/26 17:32
 */
public interface OrdersMapperCustom {

    /**
     * 关闭已超时但未支付的订单
     *
     * @return
     */
    public int closeOvertimeOrder();

    /**
     * 查询我的订单
     *
     * @param map
     * @return
     */
    public List<MyOrdersVO> queryMyOrders(@Param("paramsMap") Map<String, Object> map);
}
