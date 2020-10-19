package com.zch.service;

import com.zch.pojo.bo.UserAddressBO;
import com.zch.pojo.vo.UserAddressVO;

import java.util.List;

/**
 * @description: 用户地址 UserAddressService
 * @author: zch
 * @create: 2020-10-19 21:13
 */
public interface UserAddressService {

    /**
     * 查找用户的所有地址
     *
     * @param UserId
     * @return
     */
    public List<UserAddressVO> findAllUserAddressByUserId(String UserId);

    /**
     * 添加用户地址
     *
     * @param UserAddressBO
     * @return
     */
    public Boolean addUserAddress(UserAddressBO UserAddressBO);

    /**
     * 修改用户地址
     *
     * @param userAddressId
     * @param UserAddressBO
     * @return
     */
    public Boolean updateUserAddress(String userAddressId, UserAddressBO UserAddressBO);

    /**
     * 删除用户地址
     *
     * @param userAddressId
     * @return
     */
    public Boolean deleteUserAddress(String userAddressId);

    /**
     * 设置用户的默认地址
     *
     * @param userAddressId
     * @return
     */
    public Boolean setDefaultUserAddress(String userAddressId);
}
