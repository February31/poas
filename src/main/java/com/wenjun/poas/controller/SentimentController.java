package com.wenjun.poas.controller;

import com.wenjun.poas.entity.*;
import com.wenjun.poas.service.ICommentService;
import com.wenjun.poas.service.ITextKeywordService;
import com.wenjun.poas.service.ITextService;
import com.wenjun.poas.util.ApiResult;
import com.wenjun.poas.util.ApiResultBuilder;
import com.wenjun.poas.util.ResultCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用来展示舆情列表的controller
 *
 * @author xuwenjun
 * @date 2020/4/12
 */
@Log4j2
@RestController
public class SentimentController {
    /**
     * 1.判断是否爬完
     * 2.没爬完就返回一个数据处理中。
     * 3.爬完了，就处理数据
     */
    @Resource
    ITextService textService;
    @Resource
    ICommentService commentService;
    @Resource
    ITextKeywordService textKeywordService;

    @GetMapping("/sentiment/listTextByTime")
    public ApiResult<List<Text>> list(@RequestParam String time, @RequestParam String eventId) {
        List<Text> list = textService.getText(time, eventId);
        return ApiResultBuilder.success(list, ResultCode.SUCCESS);
    }

    /**
     * 直接点击页面的时候走这么个方法。
     *
     * @param eventId 事件id
     * @return list text
     */
    @GetMapping("/sentiment/listText")
    public ApiResult<Object> list(@RequestParam String eventId) {
        if (!textService.checkSpider(eventId)) {
            return ApiResultBuilder.success("请稍后再来，舆情数据采集中……", ResultCode.SUCCESS);
        }
        if ((!textService.checkNlp(eventId))) {
            return ApiResultBuilder.success("请稍后再来，舆情分析还有一会会儿……", ResultCode.SUCCESS);
        }
        List<Text> list = textService.getText(eventId);
        return ApiResultBuilder.success(list, ResultCode.SUCCESS);
    }

    /**
     * 列出舆情的评论
     *
     * @param weiboId weibo id
     * @return 评论列表
     */
    @GetMapping("/sentiment/listComment")
    public ApiResult<Object> listComment(@RequestParam String weiboId) {
        if (!commentService.checkSpider(weiboId)) {
            return ApiResultBuilder.success("请稍后再来，评论数据采集中……", ResultCode.SUCCESS);
        }
        if ((!commentService.checkNlp(weiboId))) {
            return ApiResultBuilder.success("请稍后再来，评论情感分析还有一会会儿……", ResultCode.SUCCESS);
        }
        List<Comment> list = commentService.getComment(weiboId);
        return ApiResultBuilder.success(list, ResultCode.SUCCESS);
    }

    @GetMapping("/sentiment/getPieData")
    public ApiResult<List<PieChartData>> getPieData(@RequestParam String eventId) {
        return ApiResultBuilder.success(textService.getPieChartData(eventId), ResultCode.SUCCESS);
    }

    @GetMapping("/sentiment/getCommentPieData")
    public ApiResult<List<PieChartData>> getCommentPieData(@RequestParam String weiboId) {
        return ApiResultBuilder.success(commentService.getPieChartData(weiboId), ResultCode.SUCCESS);
    }

    @GetMapping("/sentiment/getLineChartByDay")
    public ApiResult<List<LineChartData>> getLineChartData(@RequestParam String time, @RequestParam String eventId) {
        return ApiResultBuilder.success(textService.getByDay(time, eventId), ResultCode.SUCCESS);
    }

    @GetMapping("/sentiment/getWordCloudData")
    public ApiResult<List<WordCloudData>> getWordCloudData(@RequestParam String eventId) {
        return ApiResultBuilder.success(textKeywordService.findByEvent(eventId), ResultCode.SUCCESS);
    }

    @PostMapping("/sentiment/delete")
    public ApiResult<String> deleteSentiment(@RequestHeader("Authorization") String user, @RequestBody Text text) {
        log.info("{} {}", user, text);
        textService.deleteText(text);
        return ApiResultBuilder.success("success", ResultCode.SUCCESS);
    }

    @PostMapping("/sentiment/updateAttitude")
    public ApiResult<String> updateAttitude(@RequestHeader("Authorization") String user, @RequestBody Text text) {
        log.info("{} {}", user, text);
        textService.updateTextEmotion(text);
        return ApiResultBuilder.success("success", ResultCode.SUCCESS);
    }

    @PostMapping("/sentiment/deleteComment")
    public ApiResult<String> deleteComment(@RequestHeader("Authorization") String user, @RequestBody Comment comment) {
        log.info("{} {}", user, comment);
        commentService.deleteComment(comment);
        return ApiResultBuilder.success("success", ResultCode.SUCCESS);
    }

    @PostMapping("/sentiment/updateCommentAttitude")
    public ApiResult<String> updateCommentAttitude(@RequestHeader("Authorization") String user, @RequestBody Comment comment) {
        log.info("{} {}", user, comment);
        commentService.updateCommentAttitude(comment);
        return ApiResultBuilder.success("success", ResultCode.SUCCESS);
    }
}
