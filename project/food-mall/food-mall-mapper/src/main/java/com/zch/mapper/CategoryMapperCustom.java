package com.zch.mapper;

import com.zch.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryMapperCustom{

    public List<CategoryVO> getSubCatList(Integer rootCatId);
}