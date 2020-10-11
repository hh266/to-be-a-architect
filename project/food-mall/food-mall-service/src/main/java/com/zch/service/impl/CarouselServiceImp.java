package com.zch.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.zch.mapper.CarouselMapper;
import com.zch.pojo.Carousel;
import com.zch.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author: zch
 * @create: 2020-10-11 11:36
 */
@Service
public class CarouselServiceImp implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    /**
     * 查询所有轮播图
     *
     * @param isShow
     * @return
     */
    @Override
    public List<Carousel> queryAll(Integer isShow) {
        Example carouselExample = new Example(Carousel.class);

        //排序
        carouselExample.orderBy("sort").desc();

        //条件
        Example.Criteria  carouseCriteria = carouselExample.createCriteria();
        carouseCriteria.andEqualTo("isShow", isShow);

        return carouselMapper.selectByExample(carouselExample);

    }
}
