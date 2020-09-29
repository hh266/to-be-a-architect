package com.zch.service;

/**
 * @author: zch
 * @create: 2020-09-29 20:23
 */
public interface UserService {

    /**
     * 判断用户名是否存在
     * @param name
     * @return
     */
    public Boolean queryUserNameIsExist(String name);
}
