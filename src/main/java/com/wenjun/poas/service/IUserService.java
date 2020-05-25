package com.wenjun.poas.service;

import com.wenjun.poas.entity.User;

import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/3/31
 */
public interface IUserService {

    User findByName(String username);

    List<User> findAll();

    boolean addUser(User user);

    void changePassword(User user);

    void changeEmail(User user);

    void changeRole(User user);

    void deleteUser(User user);

    void updateUser(User user);
}
