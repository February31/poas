package com.wenjun.poas.controller;

import com.wenjun.poas.entity.Event;
import com.wenjun.poas.service.IEventService;
import com.wenjun.poas.util.ApiResult;
import com.wenjun.poas.util.ApiResultBuilder;
import com.wenjun.poas.util.ResultCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 处理事件相关的controller
 *
 * @author xuwenjun
 * @date 2020/4/15
 */
@Log4j2
@RestController
public class EventController {
    @Resource
    IEventService eventService;

    @PostMapping("/event/addEvent")
    public ApiResult<String> add(@RequestHeader("Authorization") String user, @RequestBody Event event) {
        log.info("{} {}", user, event);
        eventService.insertEvent(event);
        return ApiResultBuilder.successNoData(ResultCode.SUCCESS);
    }

    @GetMapping("/event/getEvent")
    public ApiResult<Event> get(@RequestParam String name) {
        Event event = eventService.findByName(name);
        if (event == null) {
            return ApiResultBuilder.successNoData(ResultCode.FAILED);
        }
        return ApiResultBuilder.success(event, ResultCode.SUCCESS);
    }

    @GetMapping("/event/get")
    public ApiResult<List<Event>> getAll() {
        List<Event> list = eventService.findAll();
        if (list == null) {
            return ApiResultBuilder.successNoData(ResultCode.FAILED);
        }
        return ApiResultBuilder.success(list, ResultCode.SUCCESS);
    }

    @PostMapping("/updateName")
    public ApiResult<String> updateName(@RequestHeader("Authorization") String user, @RequestBody Event event) {
        log.info("{} {}", user, event);
        eventService.updateName(event);
        return ApiResultBuilder.successNoData(ResultCode.SUCCESS);
    }

    @PostMapping("/updateKeywords")
    public ApiResult<String> updateKeywords(@RequestHeader("Authorization") String user, @RequestBody Event event) {
        log.info("{} {}", user, event);
        eventService.updateKeywords(event);
        return ApiResultBuilder.successNoData(ResultCode.SUCCESS);
    }

    @PostMapping("/updateEndTime")
    public ApiResult<String> updateEndTime(@RequestHeader("Authorization") String user, @RequestBody Event event) {
        log.info("{} {}", user, event);
        eventService.updateEndTime(event);
        return ApiResultBuilder.successNoData(ResultCode.SUCCESS);
    }

    //    暂停对事件的监控
    @PostMapping("/event/suspend")
    public ApiResult<String> suspend(@RequestHeader("Authorization") String user, @RequestBody Event event) {
        log.info("{} {}", user, event);
        eventService.suspend(event);
        return ApiResultBuilder.successNoData(ResultCode.SUCCESS);
    }

    @PostMapping("/event/updateEvent")
    public ApiResult<String> updateEvent(@RequestHeader("Authorization") String user, @RequestBody Event event) {
        log.info("{} {}", user, event);
        eventService.updateEvent(event);
        return ApiResultBuilder.successNoData(ResultCode.SUCCESS);
    }
}
