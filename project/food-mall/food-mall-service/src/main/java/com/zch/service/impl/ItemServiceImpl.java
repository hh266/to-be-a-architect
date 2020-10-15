package com.zch.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zch.Utils.PagedGridResult;
import com.zch.mapper.*;
import com.zch.pojo.*;
import com.zch.pojo.vo.ItemsCommentVO;
import com.zch.service.ItemService;
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
    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;
    @Autowired
    private ItemsMapperCustom itemsMapperCustom;


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

    /**
     * 获取商品不同等级的评论数量
     *
     * @param itemId
     * @param level
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer getItemCommentLevelCounts(String itemId, Integer level) {
        ItemsComments itemsComments = new ItemsComments();
        itemsComments.setItemId(itemId);
        itemsComments.setCommentLevel(level);
        return itemsCommentsMapper.selectCount(itemsComments);
    }

    /**
     * 获取商品不同等级的评论数量
     *
     * @param itemId
     * @param level
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult getItemCommentList(String itemId, Integer level, Integer page, Integer pageSize) {
        Map<String,Object> map = new HashMap<>(2);
        map.put("itemId",itemId);
        map.put("level",level);

        //原理：统一拦截SQL，为其提供分页功能
        PageHelper.startPage(page, pageSize);

        List<ItemsCommentVO> list = itemsMapperCustom.getItemsCommonsList(map);

        return getPageInfo(list, page);
    }

    /**
     * 获取分页结果
     *
     * @param list
     * @param page
     * @return
     */
    private PagedGridResult getPageInfo(List<?> list,Integer page){
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }


}
