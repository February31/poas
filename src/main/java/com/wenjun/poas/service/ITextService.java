package com.wenjun.poas.service;

import com.wenjun.poas.entity.LineChartData;
import com.wenjun.poas.entity.PieChartData;
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
    List<Text> getText(String eventId);

    /**
     * @param time    时间节点
     * @param eventId 事件id
     * @return 舆情列表
     */
    List<Text> getText(String time, String eventId);

    List<Text> getByNotHandled(String event);

    /**
     * 按照小时将某天的舆情数据的数量封装好，作为折线图的数据源
     *
     * @param day     日期
     * @param eventId 事件id
     * @return list
     */
    List<LineChartData> getByDay(String day, String eventId);

    /**
     * 计算正向舆情数量和负向舆情数量封装进去，作为饼图的数据源
     *
     * @param eventId event id
     * @return list
     */
    List<PieChartData> getPieChartData(String eventId);

    void deleteText(Text text);

    void insertText(Text text);

    /**
     * 修改微博情感分析结果
     *
     * @param text weiboo
     */
    void updateTextEmotion(Text text);

    void updateText(Text text);

    /**
     * @param id 事件id
     * @return true：爬虫已经运行完。false反之
     */
    Boolean checkSpider(String id);

    /**
     * @param id 事件id
     * @return true：nlp分析已完。false反之
     */
    Boolean checkNlp(String id);
}
