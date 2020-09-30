package com.zch.controller;

import cn.hutool.core.util.StrUtil;
import com.zch.result.CommonResult;
import com.zch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CommonResult usernameIsExist(String username) {
        // 1.判断用户名不能为空
        if (StrUtil.isBlank(username)) {
            // 返回参数验证失败的结果
            return CommonResult.validateFailed();
        }

        // 2.查找用户名是否存在
        boolean isExits = userService.queryUserNameIsExist(username);
        if (isExits) {
          return CommonResult.failed("用户名已存在");
        }
        return CommonResult.success("用户名不存在");
    }
}
