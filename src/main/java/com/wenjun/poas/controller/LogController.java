package com.wenjun.poas.controller;

import com.wenjun.poas.service.ILogService;
import com.wenjun.poas.util.ApiResult;
import com.wenjun.poas.util.ApiResultBuilder;
import com.wenjun.poas.util.ResultCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/5/10
 */
@Log4j2
@RestController
public class LogController {
    @Resource
    ILogService logService;

    @GetMapping("/log/getService")
    public ApiResult<List<String>> getServiceLog(@RequestParam String time, @RequestParam String username) {
        return ApiResultBuilder.success(logService.getService(time, username), ResultCode.SUCCESS);
    }

    @GetMapping("/log/getLogin")
    public ApiResult<List<String>> getLoginLog(@RequestParam String time) {
        return ApiResultBuilder.success(logService.getLogin(time), ResultCode.SUCCESS);
    }

    @GetMapping("/log/getError")
    public ApiResult<List<String>> getErrorLog(@RequestParam String time) {
        return ApiResultBuilder.success(logService.getError(time), ResultCode.SUCCESS);
    }
}
