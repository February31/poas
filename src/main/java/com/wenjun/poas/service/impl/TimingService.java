package com.wenjun.poas.service.impl;

import com.wenjun.poas.config.spider.SpiderConfig;
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

    @Scheduled(initialDelay = 5000,fixedRateString = "${spider.intervaltime}")
//    @Scheduled(initialDelay = 10000, fixedRate = 2000)
    @Override
    public void runSpiders() {
        /**
         * 1.找出有哪些事件还没有结束
         * 2.按个执行他们的爬虫，以及分析……
         */
//        List<Event> events = eventService.findNotFinishedEvent();
//        for (int i = 0; i < events.size(); i++) {
//            spiderService.runTextSpider(events.get(i).getKeywords(),events.get(i).getId().toString());
//        }
        System.out.println("123");
    }
}
