package com.wenjun.poas.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用来接收请求接口的返回数据。（后台调用其他接口）
 * @author xuwenjun
 * @date 2020/4/9
 */
@Data
public class HttpResult implements Serializable {

    // 响应的状态码
    private int code = 200;

    // 响应的响应体
    private String body;

}
