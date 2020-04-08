package com.wenjun.poas.service.impl;

import com.wenjun.poas.entity.User;
import com.wenjun.poas.mapper.IUserMapper;
import com.wenjun.poas.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xuwenjun
 * @date 2020/3/31
 */
@Service
public class UserService implements IUserService {
    @Resource
    IUserMapper userMapper;

    @Override
    public boolean addUser(User user) {
        //        todo 事务还没有加，还没有考虑到失败的情况
        userMapper.addUser(user);
        return true;
    }

    @Override
    public boolean changePassword(User user) {
//        todo 事务还没有加，还没有考虑到失败的情况
        userMapper.changePassword(user);
        return true;
    }
}
