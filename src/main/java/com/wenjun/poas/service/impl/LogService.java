package com.wenjun.poas.service.impl;

import com.wenjun.poas.client.LogReader;
import com.wenjun.poas.service.ILogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/5/11
 */
@Service
public class LogService implements ILogService {
    @Resource
    LogReader logReader;

    @Override
    public List<String> getService(String time, String username) {
        List<String> list;
        if (isToday(time)) {
            list = logReader.read("./log/service.log");
        } else {
            list = logReader.read("./log/service/" + time + ".log");
        }
        if (list.size() == 0) {
            return list;
        }
        list.removeIf(s -> !s.contains(username));
        return list;
    }

    @Override
    public List<String> getLogin(String time) {
        List<String> list;
        if (isToday(time)) {
            list = logReader.read("./log/login.log");
        } else {
            list = logReader.read("./log/login/" + time + ".log");
        }
        return list;
    }

    @Override
    public List<String> getError(String time) {
        List<String> list;
        if (isToday(time)) {
            list = logReader.read("./log/error.log");
        } else {
            list = logReader.read("./log/error/" + time + ".log");
        }
        return list;
    }

    private Boolean isToday(String time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(new Date());
        return now.equals(time);
    }
}
