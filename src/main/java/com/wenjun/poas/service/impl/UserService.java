package com.wenjun.poas.service.impl;

import com.wenjun.poas.entity.User;
import com.wenjun.poas.mapper.IUserMapper;
import com.wenjun.poas.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/3/31
 */
@Service
public class UserService implements IUserService {
    @Resource
    IUserMapper userMapper;

    @Override
    public User findByName(String username) {
        return userMapper.findUser(username);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public boolean addUser(User user) {
        //        todo 事务还没有加，还没有考虑到失败的情况
        userMapper.addUser(user);
        return true;
    }

    @Override
    public void changePassword(User user) {
//        todo 事务还没有加，还没有考虑到失败的情况
        userMapper.changePassword(user);
    }

    @Override
    public void changeEmail(User user) {
        userMapper.changeEmail(user);
    }

    @Override
    public void changeRole(User user) {
        if ("admin".equals(user.getRole())) {
            user.setRole("user");
        } else {
            user.setRole("admin");
        }
        userMapper.changeRole(user);
    }

    @Override
    public void deleteUser(User user) {
        userMapper.deleteUser(user.getId());
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
