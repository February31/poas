package com.wenjun.poas.service;

import com.wenjun.poas.entity.User;

/**
 * @author xuwenjun
 * @date 2020/3/31
 */
public interface IUserService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 修改密码
     * @param user
     * @return
     */
    boolean changePassword(User user);
}
