package com.wenjun.poas.entity;

import lombok.Data;

/**
 * @author xuwenjun
 * @date 2020/5/4
 */
@Data
public class Warning {

    private String unit;
    private String max;
    private String type;
    private String eventId;
    private String userId;
    private String time;
    private String size;

}
