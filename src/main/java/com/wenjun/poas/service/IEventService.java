package com.wenjun.poas.service;

import com.wenjun.poas.entity.Event;

import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/15
 */
public interface IEventService {
    void insertEvent(Event event);

    Event findByName(String name);

    List<Event> findAll();

    List<Event> findNotFinishedEvent();

    /**
     * 检查一下事件状态是否正常（已经到结束时间，但状态依旧未结束），不正常的话修改事件状态
     *
     * @param event 事件
     * @return 状态
     */
    String checkStatus(Event event);

    void updateName(Event event);

    void updateKeywords(Event event);

    void updateEndTime(Event event);

    /**
     * set the event status==start
     *
     * @param event event
     */
    void start(Event event);

    void suspend(Event event);

    void finish(Event event);

    /**
     * 更新事件的end time、name、keywords、filter words，用于对接前端事件修改。
     *
     * @param event event
     */
    void updateEvent(Event event);

}
