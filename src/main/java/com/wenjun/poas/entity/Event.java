package com.wenjun.poas.entity;

import lombok.Data;

/**
 * 一次分析的事件
 *
 * @author xuwenjun
 * @date 2020/4/14
 */
@Data
public class Event {
    private String id;
    private String name;
    private String keywords;
    private String filterWords;
    private String startTime;
    private String endTime;
    private String status;
    private String addFilterTime;
}
