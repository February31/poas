package com.wenjun.poas.service.impl;

import com.wenjun.poas.client.SpiderClient;
import com.wenjun.poas.config.spider.SpiderConfig;
import com.wenjun.poas.entity.HttpResult;
import com.wenjun.poas.service.ISpiderService;
import com.wenjun.poas.util.TimerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**跟爬虫相关的service
 * @author xuwenjun
 * @date 2020/4/9
 */
@Service
public class SpiderService implements ISpiderService {
    @Resource
    SpiderClient spiderClient;
    @Resource
    SpiderConfig spiderConfig;
    @Resource
    TimerUtil timerUtil;

    @Override
    public HttpResult runTextSpider(List<String> keywords) {
        Boolean running = spiderClient.runTextSpider(keywords);
        //开启监控爬虫运行结果
        //匿名方法
        Runnable runnable = () -> {
            SpiderClient spiderClient = new SpiderClient();
            spiderClient.listJobs(spiderConfig.projectName);
        };
        final long time = 5;//延迟执行实际：秒
        timerUtil.scheduleAtFixedRate(runnable,time,60);
        HttpResult result = new HttpResult();
        result.setBody(running.toString());
        return result;
    }

    @Override
    public HttpResult runCommentSpider(String textId) {
        Boolean running = spiderClient.runCommentSpider(textId);
        HttpResult result = new HttpResult();
        result.setBody(running.toString());
        return result;
    }
}
