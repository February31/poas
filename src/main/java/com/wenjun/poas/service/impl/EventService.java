package com.wenjun.poas.service.impl;

import com.wenjun.poas.entity.Event;
import com.wenjun.poas.mapper.IEventMapper;
import com.wenjun.poas.service.IEventService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xuwenjun
 * @date 2020/4/15
 */
@Service
public class EventService implements IEventService {
    @Resource
    IEventMapper eventMapper;

    @Override
    public void insertEvent(Event event) {
        eventMapper.insertEvent(event);
    }

    @Override
    public Event findByName(String name) {
        return eventMapper.findByName(name);
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
}
