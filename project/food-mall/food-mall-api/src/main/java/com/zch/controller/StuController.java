package com.zch.controller;

import com.zch.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/get")
    public Object getStuInfo(int id){
        return stuService.getStuInfo(id);
    }
}
