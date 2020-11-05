package com.zch.center.service.impl;

import com.github.pagehelper.PageInfo;
import com.zch.utils.PagedGridResult;

import java.util.List;

/**
 * @author: zch
 * @create: 2020-11-05 21:26
 */
public class BaseService {

    public PagedGridResult setterPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
