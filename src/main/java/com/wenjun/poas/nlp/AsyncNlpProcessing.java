package com.wenjun.poas.nlp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wenjun.poas.client.SpiderClient;
import com.wenjun.poas.config.spider.SpiderConfig;
import com.wenjun.poas.entity.Comment;
import com.wenjun.poas.entity.HttpResult;
import com.wenjun.poas.entity.Text;
import com.wenjun.poas.service.ICommentService;
import com.wenjun.poas.service.INlpService;
import com.wenjun.poas.service.ITextService;
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
            processingText(map.get("event").toString());
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
    private void processingText(String event) {
        List<Text> list = textService.getByNotHandled(event);
//        nlpService.dateFormat(list);
        nlpService.extractKeywords(list);
//        nlpService.sentimentAnalysisText(list);
//        nlpService.similarityAnalysis(list);
//        把list写回数据库
        for(Text text:list){
            textService.updateText(text);
        }
        System.out.println("分析完毕");
    }

    private void processingComment(String textId) {
        List<Comment> list = commentService.getComment(textId);
        nlpService.sentimentAnalysisComment(list);
//        把list写回数据库
        for (Comment comment:list){
            commentService.updateCommentAttitude(comment);
        }
    }
}
