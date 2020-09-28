package com.zch.controller;

import com.zch.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zch
 * @date 2020/9/28 15:07
 */
@RestController
@RequestMapping("/stu")
public class StuController {

    @Autowired
    private StuService stuService;

    /**
     * 查找
     * @param id
     * @return
     */
    @RequestMapping("/get")
    public Object getStuInfo(int id){
        return stuService.getStuInfo(id);
    }

    /**
     * 更新
     * @param id
     * @return
     */
    @PostMapping("update")
    public Object updateStu(int id){
        return stuService.updateStu(id);
    }

    /**
     * 删
     * @param id
     * @return
     */
    @PostMapping("delete")
    public Object deleteStu(int id){
        return stuService.deleteStu(id);
    }

    /**
     * 增
     * @return
     */
    @PostMapping("save")
    public Object saveStu(){
        return stuService.saveStu();
    }


}
