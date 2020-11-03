package com.zch.center.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zch.center.service.CenterUserService;
import com.zch.mapper.UsersMapper;
import com.zch.pojo.Users;
import com.zch.pojo.bo.center.CenterUserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author zch
 * @date 2020/11/2 11:36
 */
@Service
public class CenterUserServiceImpl  implements CenterUserService {

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 通过id获取用户信息
     *
     * @param userId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = {})
    @Override
    public Users getUserInfoById(String userId) {
        Users users = usersMapper.selectByPrimaryKey(userId);
        users.setPassword("");
        return usersMapper.selectByPrimaryKey(users);
    }

    /**
     * 修改用户信息 并返回最新信息
     *
     * @param userId
     * @param centerUserBO
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {})
    @Override
    public Users updateUserInfo(String userId, CenterUserBO centerUserBO) {
        Users userInfo = new Users();
        BeanUtil.copyProperties(centerUserBO, userInfo);
        userInfo.setId(userId);
        userInfo.setUpdatedTime(new Date());
        usersMapper.updateByPrimaryKeySelective(userInfo);

        return getUserInfoById(userId);
    }

    /**
     * 修改用户头像 并返回最新的信息
     *
     * @param userId
     * @param face
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {})
    @Override
    public Users updateUserFace(String userId, String face) {
        Users userInfo = new Users();
        userInfo.setId(userId);
        userInfo.setFace(face);
        userInfo.setUpdatedTime(new Date());
        usersMapper.updateByPrimaryKeySelective(userInfo);

        return getUserInfoById(userId);
    }
}
