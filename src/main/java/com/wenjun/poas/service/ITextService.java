package com.wenjun.poas.service;

import com.wenjun.poas.entity.Text;

import java.util.List;

/**
 * 提供对comment操作的service
 *
 * @author xuwenjun
 * @date 2020/4/13
 */
public interface ITextService {
    /**
     * @param eventId 事件id
     * @return 舆情列表
     */
    List<Text> getText(Integer eventId);

    /**
     * @param time    时间节点
     * @param eventId 事件id
     * @return 舆情列表
     */
    List<Text> getText(String time, Integer eventId);

    List<Text> getByNotHandled(String event);

    void deleteText(String textId);

    void insertText(Text text);

    /**
     * 修改微博情感分析结果
     *
     * @param text weiboo
     */
    void updateTextEmotion(Text text);

    void updateText(Text text);
}
