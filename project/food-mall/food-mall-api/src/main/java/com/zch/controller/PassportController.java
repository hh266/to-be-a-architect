package com.zch.controller;

import com.zch.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zch
 * @create: 2020-09-29 22:11
 */
@RestController
@RequestMapping("/passport")
public class PassportController {
    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExist")
    public int usernameIsExist(String username) {
        // 1.判断用户名不能为空
        if (StringUtils.isBlank(username)) {
            return 500;
        }

        // 2.查找用户名是否存在
        boolean isExits = userService.queryUserNameIsExist(username);
        if (!isExits) {
            return 500;
        }
        return 200;
    }
}
