package com.zch.service.impl;

import com.zch.mapper.StuMapper;
import com.zch.pojo.Stu;
import com.zch.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zch
 * @date 2020/9/28 15:01
 */
@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int deleteStu(int id) {
        return stuMapper.deleteByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateStu(int id) {
        Stu stu = new Stu();
        stu.setId(id);
        stu.setName("new name");
        stu.setAge(22);

        return stuMapper.updateByPrimaryKey(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int saveStu() {
        Stu stu = new Stu();
        stu.setName("save name");
        stu.setAge(19);

        return stuMapper.insert(stu);
    }
}
