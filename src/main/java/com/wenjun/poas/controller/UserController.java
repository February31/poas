package com.wenjun.poas.controller;

import com.wenjun.poas.entity.User;
import com.wenjun.poas.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author xuwenjun
 * @date 2020/3/31
 */
@Controller
public class UserController {
    @Resource
    IUserService userService;
    @PostMapping("/changePwd")
    public boolean changePassword(@RequestBody User user){
        return userService.changePassword(user);
    }

    @ResponseBody
    @GetMapping("/hello")
    public String sayHello(){
        return "hello world!";
    }
}
