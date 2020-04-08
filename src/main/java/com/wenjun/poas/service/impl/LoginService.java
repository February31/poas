package com.wenjun.poas.service.impl;

import com.wenjun.poas.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 登录需要查询数据库的service
 * @author xuwenjun
 * @date 2020/3/26
 */
@Service
public class LoginService implements  UserDetailsService {

    @Autowired
    IUserMapper UserMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails user = UserMapper.findUser(s);
        return user;
    }
}
