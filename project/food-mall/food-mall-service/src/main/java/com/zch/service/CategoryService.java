package com.zch.service;

import com.zch.pojo.Category;

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
}
