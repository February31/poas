package com.wenjun.poas.controller;

import com.wenjun.poas.entity.Event;
import com.wenjun.poas.service.IEventService;
import com.wenjun.poas.util.ApiResult;
import com.wenjun.poas.util.ApiResultBuilder;
import com.wenjun.poas.util.ResultCode;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 处理事件相关的controller
 *
 * @author xuwenjun
 * @date 2020/4/15
 */
@RestController
public class EventController {
    @Resource
    IEventService eventService;

    @RequestMapping("/addEvent")
    public ApiResult<String> add(@RequestBody Event event) {
        eventService.insertEvent(event);
        return ApiResultBuilder.successNoData(ResultCode.SUCCESS);
    }

    @RequestMapping("/getEvent")
    public ApiResult<Event> get(@RequestParam String name) {
        Event event = eventService.findByName(name);
        if (event == null) {
            return ApiResultBuilder.successNoData(ResultCode.FAILED);
        }
        return ApiResultBuilder.success(event, ResultCode.SUCCESS);
    }

    @RequestMapping("/updateName")
    public ApiResult<String> updateName(@RequestBody Event event) {
        eventService.updateName(event);
        return ApiResultBuilder.successNoData(ResultCode.SUCCESS);
    }

    @RequestMapping("/updateKeywords")
    public ApiResult<String> updateKeywords(@RequestBody Event event) {
        eventService.updateKeywords(event);
        return ApiResultBuilder.successNoData(ResultCode.SUCCESS);
    }

    @RequestMapping("/updateEndTime")
    public ApiResult<String> updateEndTime(@RequestBody Event event) {
        eventService.updateEndTime(event);
        return ApiResultBuilder.successNoData(ResultCode.SUCCESS);
    }
}
