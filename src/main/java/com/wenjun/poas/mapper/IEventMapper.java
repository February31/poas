package com.wenjun.poas.mapper;

import com.wenjun.poas.entity.Event;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/15
 */
@Mapper
public interface IEventMapper {
    void insertEvent(Event event);

    Event findByName(String eventName);

    Event findById(String eventId);

    List<Event> findAll();

    String checkEventStatus(String id);

    String getFilterWords(String id);

    String getFilterTime(String id);

    void updateName(Event event);

    void updateKeywords(Event event);

    void updateEndTime(Event event);

    void updateFilterWords(Event event);

    List<Event> findNotFinishedEvent();

    void updateEvent(Event event);

    void start(Event event);

    void suspend(Event event);

    void finish(Event event);
}
