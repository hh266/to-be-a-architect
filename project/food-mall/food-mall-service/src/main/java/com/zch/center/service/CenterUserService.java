package com.zch.center.service;

import com.zch.pojo.Users;
import com.zch.pojo.bo.center.CenterUserBO;
import com.zch.service.UserService;

/**
 * @author zch
 * @date 2020/11/2 11:33
 */
public interface CenterUserService {

    /**
     * 通过id获取用户信息
     *
     * @param userId
     * @return
     */
    public Users getUserInfoById(String userId);

    /**
     * 修改用户信息
     *
     * @param userId
     * @param centerUserBO
     * @return
     */
    public Users updateUserInfo(String userId, CenterUserBO centerUserBO);
}
