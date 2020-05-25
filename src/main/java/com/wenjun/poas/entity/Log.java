package com.wenjun.poas.entity;

import lombok.Data;

/**
 * @author xuwenjun
 * @date 2020/5/10
 */
@Data
public class Log {
    private String user;
    private String time;
    private String level;
    private String function;
    private String parameter;
}
