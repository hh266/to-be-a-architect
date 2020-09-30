package com.zch.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.zch.mapper.UsersMapper;
import com.zch.pojo.Users;
import com.zch.pojo.bo.UserRegistBO;
import com.zch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author: zch
 * @create: 2020-09-29 20:26
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Boolean queryUserNameIsExist(String name) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username", name);
        Users user = usersMapper.selectOneByExample(userExample);
        return user != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users register(UserRegistBO userRegistBO) {
        Users user = new Users();
        user.setUsername(userRegistBO.getUsername());
        user.setPassword(SecureUtil.md5(userRegistBO.getPassword()));
        user.setNickname(userRegistBO.getUsername());



        return null;
    }
}
