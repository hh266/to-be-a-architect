package com.zch.service.impl;


import com.zch.mapper.UserAddressMapper;
import com.zch.pojo.UserAddress;
import com.zch.pojo.bo.UserAddressBO;
import com.zch.pojo.vo.UserAddressVO;
import com.zch.service.UserAddressService;
import com.zch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 用户地址 UserAddressService
 * @author: zch
 * @create: 2020-10-19 22:09
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    UserAddressMapper userAddressMapper;

    /**
     * 查找用户的所有地址
     *
     * @param UserId
     * @return
     */
    @Override
    public List<UserAddressVO> findAllUserAddressByUserId(String UserId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(UserId);
        List<UserAddress> userAddressList = userAddressMapper.select(userAddress);


        return null;
    }

    /**
     * 添加用户地址
     *
     * @param UserAddressBO
     * @return
     */
    @Override
    public Boolean addUserAddress(UserAddressBO UserAddressBO) {
        return null;
    }

    /**
     * 修改用户地址
     *
     * @param userAddressId
     * @param UserAddressBO
     * @return
     */
    @Override
    public Boolean updateUserAddress(String userAddressId, UserAddressBO UserAddressBO) {
        return null;
    }

    /**
     * 删除用户地址
     *
     * @param userAddressId
     * @return
     */
    @Override
    public Boolean deleteUserAddress(String userAddressId) {
        return null;
    }

    /**
     * 设置用户的默认地址
     *
     * @param userAddressId
     * @return
     */
    @Override
    public Boolean setDefaultUserAddress(String userAddressId) {
        return null;
    }
}
