package com.zch.service;

import com.zch.pojo.Carousel;

import java.util.List;

/**
 * 轮播图 接口
 *
 * @author: zch
 * @create: 2020-10-11 11:33
 */
public interface CarouselService {

    /**
     * 查询所有轮播图
     *
     * @param isShow
     * @return
     */
    public List<Carousel> queryAll(Integer isShow);
}
