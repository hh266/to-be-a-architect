package com.zch.controller;

import cn.hutool.core.util.StrUtil;
import com.zch.pojo.bo.UserRegistBO;
import com.zch.result.CommonResult;
import com.zch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/regist")
    public CommonResult regist(@RequestBody UserRegistBO userRegistBO) {

        String username = userRegistBO.getUsername();
        String password = userRegistBO.getPassword();
        String confirmPwd = userRegistBO.getConfirmPassword();
        // 0. 判断密码和用户名不为空
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password) || StrUtil.isBlank(confirmPwd)) {
            return CommonResult.validateFailed("用户名或密码不能为空！");
        }

        // 1. 查询用户名是否存在
        boolean isExits = userService.queryUserNameIsExist(username);
        if (isExits) {
            return CommonResult.failed("用户名已存在！");
        }

        // 2. 密码的长度不能小于6
        if (password.length() < 6) {
            return CommonResult.validateFailed("密码的长度不能小于6！");
        }

        // 3.判断两次密码是否一致
        if (!password.equals(confirmPwd)) {
            return CommonResult.validateFailed("两次密不一致！");
        }

        // 4. 实现注册

        return CommonResult.success(userService.createUser(userRegistBO));
    }
}
