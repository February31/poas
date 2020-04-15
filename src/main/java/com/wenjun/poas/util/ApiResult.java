package com.wenjun.poas.util;

/**
 * 用来充当接口的返回类型的工具类
 * @author xuwenjun
 * @date 2020/4/12
 */
public class ApiResult<T> {

    private int code;
    private String msg;
    private T data;// 数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}