package com.zch.controller;

import cn.hutool.core.util.StrUtil;
import com.zch.pojo.bo.UserAddressBO;
import com.zch.result.CommonResult;
import com.zch.service.UserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zch
 * @date 2020/10/20 14:16
 */
@Api(value = "用户地址API", tags = {"用户地址相关API"})
@RestController
@RequestMapping("/address")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @ApiOperation(value = "查询用户的所有地址", notes = "查询用户的所有地址", httpMethod = "POST")
    @PostMapping("/list")
    public CommonResult list(@RequestParam String userId) {
        if (StrUtil.isBlank(userId)) {
            return CommonResult.validateFailed();
        }
        return CommonResult.success(userAddressService.findAllUserAddressByUserId(userId));
    }

    @ApiOperation(value = "添加用户地址", notes = "添加用户地址", httpMethod = "POST")
    @PostMapping("/add")
    public CommonResult add(@Validated @RequestBody UserAddressBO userAddressBO) {
        if (userAddressService.addUserAddress(userAddressBO)) {
            return CommonResult.success("添加成功");
        } else {
            return CommonResult.failed("添加失败");
        }

    }

    @ApiOperation(value = "修改用户地址", notes = "修改用户地址", httpMethod = "POST")
    @PostMapping("/update")
    public CommonResult update(@RequestParam("addressId") String addressId,
                               @Validated @RequestBody UserAddressBO userAddressBO) {
        if (StrUtil.isBlank(addressId)) {
            return CommonResult.validateFailed();
        }

        if (userAddressService.updateUserAddress(addressId, userAddressBO)) {
            return CommonResult.success("修改成功");
        } else {
            return CommonResult.failed("修改失败");
        }
    }

    @ApiOperation(value = "删除用户地址", notes = "删除用户地址", httpMethod = "POST")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("addressId") String addressId,
                               @RequestParam("userId") String userId) {

        if (StrUtil.isBlank(addressId) || StrUtil.isBlank(userId)) {
            return CommonResult.validateFailed();
        }

        if (userAddressService.deleteUserAddress(userId, addressId)) {
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("删除失败");
        }
    }

    @ApiOperation(value = "设置用户默认地址", notes = "设置用户默认地址", httpMethod = "POST")
    @PostMapping("/setDefault")
    public CommonResult setDefault(@RequestParam("addressId") String addressId,
                                   @RequestParam("userId") String userId) {
        if (StrUtil.isBlank(addressId) || StrUtil.isBlank(userId)) {
            return CommonResult.validateFailed();
        }

        if (userAddressService.setDefaultUserAddress(userId, addressId)) {
            return CommonResult.success("设置成功");
        } else {
            return CommonResult.failed("设置失败");
        }
    }

}
