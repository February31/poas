package com.wenjun.poas.service;

import com.wenjun.poas.entity.Event;

/**
 * @author xuwenjun
 * @date 2020/4/15
 */
public interface IEventService {
    void insertEvent(Event event);

    Event findByName(String name);

    void updateName(Event event);

    void updateKeywords(Event event);

    void updateEndTime(Event event);
}
