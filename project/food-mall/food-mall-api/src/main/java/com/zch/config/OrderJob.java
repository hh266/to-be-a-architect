package com.zch.config;

import cn.hutool.core.date.DateUtil;
import com.zch.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataUnit;

/**
 * 订单当时任务
 *
 * @author zch
 * @date 2020/10/26 16:31
 */
@Component
public class OrderJob {

    @Autowired
    private OrderService orderService;

    /**
     * 自动关闭订单
     */
    //@Scheduled(cron = "0/3 * * * * ?")
    public void autoCloseOrder(){
        System.out.println("执行定时任务，当前时间为："+ DateUtil.now());
        System.out.println("关闭订单数："+ orderService.closeOvertimeOrders());

    }
}
