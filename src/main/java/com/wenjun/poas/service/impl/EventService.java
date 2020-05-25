package com.wenjun.poas.service.impl;

import com.wenjun.poas.entity.Event;
import com.wenjun.poas.mapper.HandlingStatusMapper;
import com.wenjun.poas.mapper.IEventMapper;
import com.wenjun.poas.service.IEventService;
import com.wenjun.poas.util.DateFormatUtil;
import org.springframework.stereotype.Service;
import sun.util.calendar.LocalGregorianCalendar;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/15
 */
@Service
public class EventService implements IEventService {
    @Resource
    IEventMapper eventMapper;
    @Resource
    DateFormatUtil dateFormatUtil;
    @Resource
    HandlingStatusMapper handlingStatusMapper;

    @Override
    public void insertEvent(Event event) {
        eventMapper.insertEvent(event);
    }

    @Override
    public Event findByName(String name) {
        return eventMapper.findByName(name);
    }

    @Override
    public List<Event> findAll() {
        return eventMapper.findAll();
    }

    @Override
    public List<Event> findNotFinishedEvent() {
        return eventMapper.findNotFinishedEvent();
    }

    @Override
    public String checkStatus(Event event) {
        String status = eventMapper.checkEventStatus(event.getId());
        if ("未开始".equals(status) || "结束".equals(status)) {
            return status;
        }
        Long end = dateFormatUtil.getTime(event.getEndTime());
        long now = new Date().getTime();
        if (now < end) {
            return status;
        }
        eventMapper.finish(event);
        return status;
    }

    @Override
    public void updateName(Event event) {
        eventMapper.updateName(event);
    }

    @Override
    public void updateKeywords(Event event) {
        eventMapper.updateKeywords(event);
    }

    @Override
    public void updateEndTime(Event event) {
        eventMapper.updateEndTime(event);
    }

    @Override
    public void start(Event event) {
        eventMapper.start(event);
    }

    @Override
    public void suspend(Event event) {
//        把读数据的限制给去掉，避免如果在舆情分析的中途死掉，读数据的限制卡在那里使得以后都读不到树
        handlingStatusMapper.finishEventSpider(event.getId());
        handlingStatusMapper.finishEventNlp(event.getId());
        eventMapper.suspend(event);
    }

    @Override
    public void finish(Event event) {
        eventMapper.finish(event);
    }

    @Override
    public void updateEvent(Event event) {
        eventMapper.updateEvent(event);
    }
}
