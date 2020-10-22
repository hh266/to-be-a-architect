package com.zch.service;

import com.zch.Utils.PagedGridResult;
import com.zch.pojo.Items;
import com.zch.pojo.ItemsImg;
import com.zch.pojo.ItemsParam;
import com.zch.pojo.ItemsSpec;
import com.zch.pojo.vo.ItemsCommentVO;
import com.zch.pojo.vo.ShopcartVO;

import java.util.List;

/**
 * @description: 商品 接口
 * @author: zch
 * @create: 2020-10-13 20:36
 */
public interface ItemService {

    /**
     * 通过商品id 获得商品详情
     *
     * @param itemId
     * @return
     */
    public Items getItemById(String itemId);

    /**
     * 通过商品id 获得商品图片
     *
     * @param itemId
     * @return
     */
    public List<ItemsImg> getItemImages(String itemId);

    /**
     * 通过商品id 获得商品规格
     *
     * @param itemId
     * @return
     */
    public List<ItemsSpec> getItemItemsSpecs(String itemId);

    /**
     * 通过商品id 获得商参数
     *
     * @param itemId
     * @return
     */
    public ItemsParam getItemParam(String itemId);

    /**
     * 获取商品不同等级的评论数量
     *
     * @param itemId
     * @param level
     * @return
     */
    public Integer getItemCommentLevelCounts(String itemId, Integer level);


    /**
     * 获取商品不同等级的评论数量
     *
     * @param itemId
     * @param level
     * @return
     */
    public PagedGridResult getItemCommentList(String itemId, Integer level, Integer page, Integer pageSize);


    /**
     * 根据关键词查找商品
     *
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult getItemsByKeywords(String keywords, String sort, Integer page, Integer pageSize);

    /**
     * 根据三级分类查找商品
     *
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult getItemsByThirdCat(Integer catId, String sort, Integer page, Integer pageSize);


    /**
     * 过规格ids 查询购物车中的商品
     *
     * @param specIds
     * @return
     */
    public List<ShopcartVO> queryItemsBySpecIds(String specIds);

    /**
     * 减少商品库存
     *
     * @param specId
     * @param buyCounts
     * @return
     */
    public void decreaseItemSpecStock(String specId, Integer buyCounts);
}
