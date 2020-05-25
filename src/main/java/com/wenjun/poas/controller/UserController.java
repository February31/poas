package com.wenjun.poas.controller;

import com.wenjun.poas.entity.User;
import com.wenjun.poas.service.IUserService;
import com.wenjun.poas.util.ApiResult;
import com.wenjun.poas.util.ApiResultBuilder;
import com.wenjun.poas.util.ResultCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/3/31
 */
@Log4j2
@RestController
public class UserController {
    @Resource
    IUserService userService;

    @PostMapping("/user/changePwd")
    public ApiResult<String> changePassword(@RequestHeader("Authorization") String username, @RequestBody User user) {
        log.info("{} {}", username, user);
        userService.changePassword(user);
        return ApiResultBuilder.success("success", ResultCode.SUCCESS);
    }

    @PostMapping("/user/changeEmail")
    public ApiResult<String> changeEmail(@RequestHeader("Authorization") String username, @RequestBody User user) {
        log.info("{} {}", username, user);
        userService.changeEmail(user);
        return ApiResultBuilder.success("success", ResultCode.SUCCESS);
    }

    @GetMapping("/user/getCurrent")
    public ApiResult<User> getCurrent(@RequestParam String username) {
        return ApiResultBuilder.success(userService.findByName(username), ResultCode.SUCCESS);
    }

    @GetMapping("/user/list")
    public ApiResult<List<User>> list() {
        return ApiResultBuilder.success(userService.findAll(), ResultCode.SUCCESS);
    }

    @PostMapping("/user/add")
    public ApiResult<String> addUser(@RequestHeader("Authorization") String username, @RequestBody User user) {
        log.info("{} {}", username, user);
        userService.addUser(user);
        return ApiResultBuilder.success("success", ResultCode.SUCCESS);
    }

    @PostMapping("/user/changeRole")
    public ApiResult<String> changeRole(@RequestHeader("Authorization") String username, @RequestBody User user) {
        log.info("{} {}", username, user);
        userService.changeRole(user);
        return ApiResultBuilder.success("success", ResultCode.SUCCESS);
    }

    @PostMapping("/user/delete")
    public ApiResult<String> delete(@RequestHeader("Authorization") String username, @RequestBody User user) {
        log.info("{} {}", username, user);
        userService.deleteUser(user);
        return ApiResultBuilder.success("success", ResultCode.SUCCESS);
    }

    @PostMapping("/user/update")
    public ApiResult<String> update(@RequestHeader("Authorization") String username, @RequestBody User user) {
        log.info("{} {}", username, user);
        userService.updateUser(user);
        return ApiResultBuilder.success("success", ResultCode.SUCCESS);
    }

}
