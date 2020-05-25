package com.wenjun.poas.service.impl;

import com.wenjun.poas.aspect.annotation.SpiderMonitor;
import com.wenjun.poas.client.SpiderClient;
import com.wenjun.poas.entity.Event;
import com.wenjun.poas.entity.HttpResult;
import com.wenjun.poas.mapper.HandlingStatusMapper;
import com.wenjun.poas.mapper.IEventMapper;
import com.wenjun.poas.mapper.ITextMapper;
import com.wenjun.poas.service.ISpiderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 跟爬虫相关的service
 *
 * @author xuwenjun
 * @date 2020/4/9
 */
@Service
public class SpiderService implements ISpiderService {
    @Resource
    SpiderClient spiderClient;
    @Resource
    IEventMapper eventMapper;
    @Resource
    HandlingStatusMapper handlingStatusMapper;
    @Resource
    ITextMapper textMapper;

    @SpiderMonitor
    @Override
    public HttpResult runTextSpider(Event event) {
//        TODO 这里还要再开一个定时器，每两个小时执行一次爬虫。
        handlingStatusMapper.startEventSpider(event.getId());
        Boolean running = spiderClient.runTextSpider(event.getKeywords(), event.getId());
        HttpResult result = new HttpResult();
        result.setBody(running.toString());
//        修改事件的状态。
        eventMapper.start(event);
        return result;
    }

    @SpiderMonitor
    @Override
    public HttpResult runCommentSpider(String textId) {
        handlingStatusMapper.startCommentSpider(textId);
        Boolean running = spiderClient.runCommentSpider(textId);
        HttpResult result = new HttpResult();
        result.setBody(running.toString());
        //        修改微博正文的状态。
        textMapper.crawlComment(textId);
        return result;
    }
}
