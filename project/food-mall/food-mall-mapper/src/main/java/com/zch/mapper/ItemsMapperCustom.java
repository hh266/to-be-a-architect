package com.zch.mapper;


import com.zch.pojo.vo.ItemsCommentVO;
import com.zch.pojo.vo.SearchItemVO;
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
}