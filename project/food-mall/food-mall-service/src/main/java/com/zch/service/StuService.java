package com.zch.service;

import com.zch.pojo.Stu;

/**
 * 测试类
 * @author zch
 * @date 2020/9/28 14:54
 */
public interface StuService {

    /**
     * 根据id查找学生
     * @param id
     * @return
     */
    public Stu getStuInfo(int id);

    /**
     * 根据id删除学生
     * @param id
     */
    public void deleteStu(int id);

    /**
     * 根据id修改学生
     * @param id
     * @return
     */
    public int updateStu(int id);

    /**
     * 添加学生
     */
    public void saveStu();
}
