package com.wenjun.poas.service;

import com.wenjun.poas.entity.Log;

import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/5/10
 */
public interface ILogService {
    List<String> getService(String time, String username);

    List<String> getLogin(String time);

    List<String> getError(String time);
}
