package com.zch.service.impl;

import com.zch.mapper.CategoryMapper;
import com.zch.mapper.CategoryMapperCustom;
import com.zch.pojo.Category;
import com.zch.pojo.vo.CategoryVO;
import com.zch.pojo.vo.NewItemsVO;
import com.zch.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: zch
 * @create: 2020-10-11 18:46
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    /**
     * 查询所有一级商品分类
     *
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 1);
        return categoryMapper.selectByExample(example);
    }

    /**
     * 根据一级分类查询二三级分类信息
     *
     * @param rootCatId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        return categoryMapperCustom.getSubCatList(rootCatId);
    }

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     *
     * @param rootCatId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId) {

        Map<String,Object> map = new HashMap<>();
        map.put("rootCatId", rootCatId);

        return categoryMapperCustom.getSixNewItemsLazy(map);
    }


}
