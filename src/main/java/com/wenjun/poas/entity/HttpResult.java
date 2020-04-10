package com.wenjun.poas.entity;

import java.io.Serializable;

/**
 * @author xuwenjun
 * @date 2020/4/9
 */
public class HttpResult implements Serializable {

    // 响应的状态码
    private int code;

    // 响应的响应体
    private String body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
