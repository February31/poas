package com.wenjun.poas.controller;

import com.wenjun.poas.entity.User;
import com.wenjun.poas.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xuwenjun
 * @date 2020/3/31
 */
@RestController
public class UserController {
    @Resource
    IUserService userService;

    @PostMapping("/changePwd")
    public boolean changePassword(@RequestBody User user) {
        return userService.changePassword(user);
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello world!";
    }
}
