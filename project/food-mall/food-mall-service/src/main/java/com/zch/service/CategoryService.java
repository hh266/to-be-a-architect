package com.zch.service;

import com.zch.pojo.Category;
import com.zch.pojo.vo.CategoryVO;
import com.zch.pojo.vo.NewItemsVO;

import java.util.List;

/**
 * @description:
 * @author: zch
 * @create: 2020-10-11 18:39
 */
public interface CategoryService {

    /**
     * 查询所有一级分类
     *
     * @return
     */
    public List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类查询二三级分类信息
     * @param rootCatId
     * @return
     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     * @param rootCatId
     * @return
     */
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
