package com.zch.mapper;


import com.zch.pojo.vo.ItemsCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom {
    public List<ItemsCommentVO> getItemsCommonsList(@Param("paramsMap") Map<String, Object> map);
}