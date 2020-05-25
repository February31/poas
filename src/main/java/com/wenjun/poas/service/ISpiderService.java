package com.wenjun.poas.service;

import com.wenjun.poas.entity.Event;
import com.wenjun.poas.entity.HttpResult;

import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/9
 */
public interface ISpiderService {
    /**
     * @param event 事件
     * @return is running
     */
    HttpResult runTextSpider(Event event);

    HttpResult runCommentSpider(String textId);
}
