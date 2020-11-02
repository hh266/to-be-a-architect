package com.zch.controller.center;

import com.zch.center.service.CenterUserService;
import com.zch.pojo.Users;
import com.zch.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户中心
 *
 * @author zch
 * @date 2020/11/2 10:49
 */
@Api(value = "用户中心的接口", tags = {"用于用户中心的接口"})
@RestController
@RequestMapping("/center")
public class CenterController {

    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息", httpMethod = "GET")
    @GetMapping("/userInfo")
    public CommonResult userInfo(String userId){
        return CommonResult.success(centerUserService.getUserInfoById(userId));
    }
}
