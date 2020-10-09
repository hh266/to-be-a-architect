package com.zch.service;

import com.zch.pojo.Users;
import com.zch.pojo.bo.UserRegistBO;

/**
 * @author: zch
 * @create: 2020-09-29 20:23
 */
public interface UserService {

    /**
     * 判断用户名是否存在
     *
     * @param name
     * @return
     */
    public Boolean queryUserNameIsExist(String name);

    /**
     * 创建用户
     *
     * @param userRegistBO
     * @return
     */
    public Users createUser(UserRegistBO userRegistBO);

    /**
     * 检索用户密码是否匹配，用于用户登录
     *
     * @param username
     * @param password
     * @return
     */
    public Users queryUserForLogin(String username, String password);
}
