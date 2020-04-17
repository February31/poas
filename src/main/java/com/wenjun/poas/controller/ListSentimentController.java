package com.wenjun.poas.controller;

import com.wenjun.poas.entity.Text;
import com.wenjun.poas.service.ITextService;
import com.wenjun.poas.util.ApiResult;
import com.wenjun.poas.util.ApiResultBuilder;
import com.wenjun.poas.util.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用来展示舆情列表的controller
 *
 * @author xuwenjun
 * @date 2020/4/12
 */
@RestController
public class ListSentimentController {
    /**
     * 1.判断是否爬完
     * 2.没爬完就返回一个数据处理中。
     * 3.爬完了，就处理数据
     */
    @Resource
    ITextService textService;

    @GetMapping("/listTextByTime")
    public ApiResult<List<Text>> list(@RequestParam String time, @RequestParam Integer eventId) {
        List<Text> list = textService.getText(time, eventId);
        return ApiResultBuilder.success(list, ResultCode.SUCCESS);
    }

    /**
     * 直接点击页面的时候走这么个方法。
     *
     * @param eventId 事件id
     * @return list text
     */
    @GetMapping("/listText")
    public ApiResult<List<Text>> list(@RequestParam Integer eventId) {
        List<Text> list = textService.getText(eventId);
        return ApiResultBuilder.success(list, ResultCode.SUCCESS);
    }

}
