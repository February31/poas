package com.wenjun.poas.service;

import com.wenjun.poas.entity.Text;
import com.wenjun.poas.entity.Warning;

import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/5/4
 */
public interface IWarningService {
    void addWarning(Warning warning);

    void deleteWarning(Warning warning);

    /**
     * 通过事件来查找预警，一个事件只有一个预警
     *
     * @param eventId 事件id
     * @return 唯一的预警
     */
    Warning findWarning(String eventId);

    /**
     * 通过用户来找他设置的预警。
     *
     * @param userId 用户id
     * @return 一组事件
     */
    List<Warning> findByUser(String userId);

    List<Warning> findByUserAll(String userId);

    /**
     * 计算在这次处理中，是否达到舆情预警条件.
     * 计算爬取下来时，createdAt为刚刚或者x分钟前的。
     *
     * @param eventId event id
     * @return 是否达到预警条件
     */
    Boolean fit(String eventId, List<Text> list);

    void sendEmail(String eventId);

    void updateTimeAndSize(Warning warning);

    /**
     * 只能更新type、unit、max三个属性。userId暂时不可更改，第一个创建的人是谁，就给谁报警。
     *
     * @param warning warning
     */
    void updateWarning(Warning warning);

    Integer getSize();

    /**
     * 当前端接受预警之后，需要将这一次的预警从数据库删除掉。就调用这个方法。
     *
     * @param eventId event id
     */
    void handledWarning(String eventId);
}
