package com.wenjun.poas.nlp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wenjun.poas.client.SpiderClient;
import com.wenjun.poas.config.spider.SpiderConfig;
import com.wenjun.poas.entity.*;
import com.wenjun.poas.mapper.HandlingStatusMapper;
import com.wenjun.poas.service.ICommentService;
import com.wenjun.poas.service.INlpService;
import com.wenjun.poas.service.ITextService;
import com.wenjun.poas.service.IWarningService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 异步监控爬虫和爬虫结束后处理数据！
 *
 * @author xuwenjun
 * @date 2020/4/16
 */
@Component
public class AsyncNlpProcessing {
    @Resource
    SpiderClient spiderClient;
    @Resource
    SpiderConfig spiderConfig;
    @Resource
    ITextService textService;
    @Resource
    INlpService nlpService;
    @Resource
    ICommentService commentService;
    @Resource
    IWarningService warningService;
    @Resource
    HandlingStatusMapper handlingStatusMapper;

    @Async
    public void monitorSpider(Map<String, Object> map) {
        boolean isRunning;
//        如果爬虫没有结束，就一直卡着
        do {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            HttpResult result = spiderClient.listJobs(spiderConfig.projectName);
            JSONObject jsonResult = JSONObject.parseObject(result.getBody());
            System.out.println(jsonResult);
            JSONArray running = jsonResult.getJSONArray("running");
            JSONArray pending = jsonResult.getJSONArray("pending");

            isRunning = pending.size() > 0 || running.size() > 0;
        } while (isRunning);
        if (map.containsKey("event")) {
            Event event = (Event) map.get("event");
            processingText(event.getId());
        } else {
            processingComment(map.get("textId").toString());
        }
    }

    //        爬虫结束了，开始处理数据了！
//    /**
//     * 1.先把要分析的数据从数据库取出来。
//     * 2.分析
//     * 3.存回去
//     */

    /**
     * 1.把数据全部取出来，然后一一丢进nlpservice的方法中
     */
    private void processingText(String eventId) {
//        关闭爬虫状态监控，表示事件爬虫结束
        handlingStatusMapper.finishEventSpider(eventId);
//        开启nlp处理监控，表示舆情分析进行中
        handlingStatusMapper.startEventNlp(eventId);
        List<Text> list = textService.getByNotHandled(eventId);
        //进行是否触发报警判读
        if (warningService.fit(eventId, list)) {
            Warning warning = warningService.findWarning(eventId);
//            如果报警方式是邮件就发邮件，不是则把这一次的舆情报警时间和舆情条数记录下来
            warning.setSize(warningService.getSize().toString());
            warning.setTime(list.get(0).getCrawledAt());
            warningService.updateTimeAndSize(warning);
            if ("email".equals(warning.getType())) {
                warningService.sendEmail(eventId);
            }
        }
        nlpService.dateFormat(list);
        nlpService.extractKeywords(list);
        nlpService.sentimentAnalysisText(list);
        nlpService.similarityAnalysis(list);
//        把list写回数据库
        for (Text text : list) {
//            System.out.println(text);
            textService.updateText(text);
        }
        //        结束nlp处理监控，表示舆情分析完毕
        handlingStatusMapper.finishEventNlp(eventId);
        System.out.println("分析完毕");
    }

    private void processingComment(String textId) {
        //        关闭爬虫状态监控，表示评论爬虫结束
        handlingStatusMapper.finishCommentSpider(textId);
//        开启nlp处理监控，表示评论舆情分析进行中
        handlingStatusMapper.startCommentNlp(textId);
        List<Comment> list = commentService.getComment(textId);
        nlpService.sentimentAnalysisComment(list);
//        把list写回数据库
        for (Comment comment : list) {
            commentService.updateCommentAttitude(comment);
        }
//        结束nlp处理监控，表示舆情分析完毕
        handlingStatusMapper.finishCommentNlp(textId);
    }
}
