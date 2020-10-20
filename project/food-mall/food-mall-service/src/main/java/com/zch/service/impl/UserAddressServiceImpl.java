package com.zch.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.zch.enums.YseOrNo;
import com.zch.mapper.UserAddressMapper;
import com.zch.pojo.UserAddress;
import com.zch.pojo.bo.UserAddressBO;
import com.zch.pojo.vo.UserAddressVO;
import com.zch.service.UserAddressService;
import com.zch.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 用户地址 UserAddressService
 * @author: zch
 * @create: 2020-10-19 22:09
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private Sid sid;

    /**
     * 查找用户的所有地址
     *
     * @param UserId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = {})
    @Override
    public List<UserAddressVO> findAllUserAddressByUserId(String UserId) {
        UserAddress address = new UserAddress();
        address.setUserId(UserId);
        List<UserAddress> userAddressList = userAddressMapper.select(address);

        List<UserAddressVO> userAddressVOList = userAddressList.stream().map(userAddress -> {
            return BeanUtil.copyProperties(userAddress, UserAddressVO.class);
        }).collect(Collectors.toList());

        return userAddressVOList;
    }

    /**
     * 添加用户地址
     *
     * @param UserAddressBO
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {})
    @Override
    public Boolean addUserAddress(UserAddressBO UserAddressBO) {
        UserAddress userAddress = BeanUtil.copyProperties(UserAddressBO, UserAddress.class);
        userAddress.setId(sid.nextShort());
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());

        //查找该用户是否已有地址，如果没有设置改地址为默认地址
        List<UserAddressVO> userAddressVOList = findAllUserAddressByUserId(userAddress.getUserId());
        if (userAddressVOList == null) {
            userAddress.setIsDefault(YseOrNo.YES.type);
        } else {
            userAddress.setIsDefault(YseOrNo.NO.type);
        }

        return 1 == userAddressMapper.insert(userAddress);
    }

    /**
     * 修改用户地址
     *
     * @param userAddressId
     * @param UserAddressBO
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {})
    @Override
    public Boolean updateUserAddress(String userAddressId, UserAddressBO UserAddressBO) {
        UserAddress userAddress = BeanUtil.copyProperties(UserAddressBO, UserAddress.class);
        userAddress.setId(userAddressId);
        userAddress.setUpdatedTime(new Date());

        return 1 == userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }

    /**
     * 删除用户地址
     *
     * @param userId
     * @param userAddressId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {})
    @Override
    public Boolean deleteUserAddress(String userId, String userAddressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(userAddressId);
        userAddress.setUserId(userId);

        return 1 == userAddressMapper.delete(userAddress);
    }

    /**
     * 设置用户的默认地址
     *
     * @param userAddressId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {})
    @Override
    public Boolean setDefaultUserAddress(String userId, String userAddressId) {
        //把该用户的默认地址设置为非默认
        Example example = new Example(UserAddress.class);
        Example.Criteria Criteria = example.createCriteria();
        Criteria.andEqualTo("userId", userId);
        Criteria.andEqualTo("isDefault", 1);

        UserAddress userAddress = new UserAddress();
        userAddress.setIsDefault(0);
        userAddress.setUpdatedTime(new Date());

        userAddressMapper.updateByExampleSelective(userAddress, example);

        //设置默认地址
        UserAddress address = new UserAddress();
        address.setId(userAddressId);
        address.setIsDefault(1);
        address.setUpdatedTime(new Date());

        return 1 == userAddressMapper.updateByPrimaryKeySelective(address);
    }

    /**
     * 通过 userAddressId 查找用户id
     *
     * @param userAddressId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = {})
    @Override
    public UserAddress getUserAddressById(String userAddressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(userAddressId);
        return userAddressMapper.selectByPrimaryKey(userAddress);
    }

}
