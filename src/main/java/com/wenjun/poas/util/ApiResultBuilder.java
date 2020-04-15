package com.wenjun.poas.util;

import org.springframework.stereotype.Component;

/**
 * @author xuwenjun
 * @date 2020/4/12
 */
public class ApiResultBuilder {

    //成功，不返回具体数据
    public static <T> ApiResult<T> successNoData(ResultCode code){
        ApiResult<T> result = new ApiResult<>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }
    //成功，返回数据
    public static <T> ApiResult<T> success(T t,ResultCode code){
        ApiResult<T> result = new ApiResult<>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        result.setData(t);
        return result;
    }

    //失败，返回失败信息
    public static <T> ApiResult<T> faile(ResultCode code){
        ApiResult<T> result = new ApiResult<>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }

}

