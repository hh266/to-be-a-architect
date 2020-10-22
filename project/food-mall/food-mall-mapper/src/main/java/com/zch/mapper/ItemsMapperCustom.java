package com.zch.mapper;


import com.zch.pojo.vo.ItemsCommentVO;
import com.zch.pojo.vo.SearchItemVO;
import com.zch.pojo.vo.ShopcartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author zch
 */
public interface ItemsMapperCustom {

    /**
     * 获取商品评论列表
     *
     * @param map
     * @return
     */
    public List<ItemsCommentVO> getItemsCommonsList(@Param("paramsMap") Map<String, Object> map);

    /**
     * 通过关键字搜索商品
     *
     * @param map
     * @return
     */
    public List<SearchItemVO> getItemsByKeywords(@Param("paramsMap") Map<String, Object> map);

    /**
     * 通过关键字搜索商品
     *
     * @param map
     * @return
     */
    public List<SearchItemVO> getItemsByThirdCat(@Param("paramsMap") Map<String, Object> map);


    /**
     * 通过规格ids 查询购物车中的商品
     *
     * @param specIdsList
     * @return
     */
    public List<ShopcartVO> queryItemsBySpecIds(@Param("paramsList") List specIdsList);


    /**
     * 减少库存
     *
     * @param specId
     * @param buyCounts
     * @return
     */
    public Integer decreaseItemSpecStock(@Param("specId") String specId,
            @Param("buyCounts") Integer buyCounts);

}