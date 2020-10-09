package com.zch.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.zch.enums.Sex;
import com.zch.mapper.UsersMapper;
import com.zch.pojo.Users;
import com.zch.pojo.bo.UserRegistBO;
import com.zch.service.UserService;
import org.apache.ibatis.jdbc.Null;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author: zch
 * @create: 2020-09-29 20:26
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    private static final String User_Face = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

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
    public Users createUser(UserRegistBO userRegistBO) {
        String userId = sid.nextShort();

        Users user = new Users();
        user.setUsername(userRegistBO.getUsername());
        user.setPassword(SecureUtil.md5(userRegistBO.getPassword()));
        user.setNickname(userRegistBO.getUsername());

        user.setId(userId);

        //默认用户昵称同用户名
        user.setNickname(userRegistBO.getUsername());
        //m设置默认用户头像
        user.setFace(User_Face);
        //默认生日
        user.setBirthday(DateUtil.parse("1900-01-01"));
        //设置默认性别 保密
        user.setSex(Sex.secret.type);

        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insert(user);
        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username", username);
        userCriteria.andEqualTo("password", SecureUtil.md5(password));
        Users user = usersMapper.selectOneByExample(userExample);
        user = setNullProperty(user);
        return user;
    }


    /**
     * 过滤用户隐私信息
     *
     * @param user
     * @return
     */
    private Users setNullProperty(Users user) {
        user.setRealname(null);
        user.setPassword(null);
        user.setBirthday(null);
        user.setUpdatedTime(null);
        user.setEmail(null);
        user.setMobile(null);
        return user;
    }
}
