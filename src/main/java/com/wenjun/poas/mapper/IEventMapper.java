package com.wenjun.poas.mapper;

import com.wenjun.poas.entity.Event;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xuwenjun
 * @date 2020/4/15
 */
@Mapper
public interface IEventMapper {
    void insertEvent(Event event);

    Event findByName(String eventName);

    void updateName(Event event);

    void updateKeywords(Event event);

    void updateEndTime(Event event);
}
