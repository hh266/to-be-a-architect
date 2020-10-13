package com.zch.service.impl;

import com.zch.mapper.ItemsImgMapper;
import com.zch.mapper.ItemsMapper;
import com.zch.mapper.ItemsParamMapper;
import com.zch.mapper.ItemsSpecMapper;
import com.zch.pojo.Items;
import com.zch.pojo.ItemsImg;
import com.zch.pojo.ItemsParam;
import com.zch.pojo.ItemsSpec;
import com.zch.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @description:
 * @author: zch
 * @create: 2020-10-13 21:12
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;
    @Autowired
    private ItemsImgMapper itemsImgMapper;
    @Autowired
    private ItemsSpecMapper itemsSpecMapper;
    @Autowired
    private ItemsParamMapper itemsParamMapper;


    /**
     * 通过商品id 获得商品详情
     *
     * @param itemId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items getItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    /**
     * 通过商品id 获得商品图片
     *
     * @param itemId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> getItemImages(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);

        return itemsImgMapper.selectByExample(example);
    }

    /**
     * 通过商品id 获得商品规格
     *
     * @param itemId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> getItemItemsSpecs(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);

        return itemsSpecMapper.selectByExample(example);
    }

    /**
     * 通过商品id 获得商参数
     *
     * @param itemId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam getItemParam(String itemId) {
        Example example = new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);

        return itemsParamMapper.selectOneByExample(example);
    }
}
