package com.wenjun.poas.service.impl;

import com.wenjun.poas.entity.Event;
import com.wenjun.poas.service.IEventService;
import com.wenjun.poas.service.ISpiderService;
import com.wenjun.poas.service.ITimingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/16
 */
@Component
public class TimingService implements ITimingService {
    @Resource
    IEventService eventService;

    @Resource
    ISpiderService spiderService;

    @Scheduled(initialDelayString = "${spider.initialDelay}", fixedRateString = "${spider.intervalTime}")
//    @Scheduled(initialDelay = 10000, fixedRate = 2000)
    @Override
    public void runSpiders() {
//        /**
//         * 1.找出有哪些事件还没有结束
//         * 2.按个执行他们的爬虫，以及分析……
//         * 3.按理说各爬虫应该异步爬，但是现在不知道scrapy能不能支持异步，先同步尝试。
//         */
        List<Event> events = eventService.findNotFinishedEvent();
        for (int i = 0; i < events.size(); i++) {
            if ("进行中".equals(eventService.checkStatus(events.get(i)))) {
                spiderService.runTextSpider(events.get(i));
            }
        }
    }
}
