package com.zch.service;

import com.zch.pojo.Items;
import com.zch.pojo.ItemsImg;
import com.zch.pojo.ItemsParam;
import com.zch.pojo.ItemsSpec;

import java.util.List;

/**
 * @description: 商品 接口
 * @author: zch
 * @create: 2020-10-13 20:36
 */
public interface ItemService {

    /**
     * 通过商品id 获得商品详情
     * @param itemId
     * @return
     */
    public Items getItemById(String itemId);

    /**
     * 通过商品id 获得商品图片
     * @param itemId
     * @return
     */
    public List<ItemsImg> getItemImages(String itemId);

    /**
     * 通过商品id 获得商品规格
     * @param itemId
     * @return
     */
    public List<ItemsSpec> getItemItemsSpecs(String itemId);

    /**
     * 通过商品id 获得商参数
     * @param itemId
     * @return
     */
    public ItemsParam getItemParam(String itemId);
}
