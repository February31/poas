package com.wenjun.poas.controller;

import com.alibaba.druid.util.StringUtils;
import com.wenjun.poas.entity.Warning;
import com.wenjun.poas.service.IUserService;
import com.wenjun.poas.service.IWarningService;
import com.wenjun.poas.util.ApiResult;
import com.wenjun.poas.util.ApiResultBuilder;
import com.wenjun.poas.util.ResultCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 关于报警的controller
 *
 * @author xuwenjun
 * @date 2020/5/5
 */
@Log4j2
@RestController
public class WarningController {
    @Resource
    IWarningService warningService;
    @Resource
    IUserService userService;

    @PostMapping("/warning/add")
    public ApiResult<String> addWarning(@RequestHeader("Authorization") String username, @RequestBody Warning warning) {
        log.info("{} {}", username, warning);
        warningService.addWarning(warning);
        return ApiResultBuilder.success("success", ResultCode.SUCCESS);
    }

    /**
     * 用来给前端提供站内报警的接口
     *
     * @param userId user id
     * @return list warning
     */
    @GetMapping("/warning/get")
    public ApiResult<List<Warning>> getWarning(@RequestParam String userId) {
        if (!StringUtils.isNumber(userId)) {
            userId = userService.findByName(userId).getId();
        }
        return ApiResultBuilder.success(warningService.findByUser(userId), ResultCode.SUCCESS);
    }

    @PostMapping("/warning/update")
    public ApiResult<String> updateWarning(@RequestHeader("Authorization") String username, @RequestBody Warning warning) {
        log.info("{} {}", username, warning);
        warningService.updateWarning(warning);
        return ApiResultBuilder.success("success", ResultCode.SUCCESS);
    }

    @PostMapping("/warning/handled")
    public ApiResult<String> handledWarning(@RequestHeader("Authorization") String username, @RequestParam String eventId) {
        log.info("{} {}", username, eventId);
        warningService.handledWarning(eventId);
        return ApiResultBuilder.success("success", ResultCode.SUCCESS);
    }
}
