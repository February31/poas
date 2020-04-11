package com.wenjun.poas.service;

import com.wenjun.poas.entity.HttpResult;

import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/9
 */
public interface ISpiderService {
    /**
     * @param keywords 关键词
     * @return is running
     */
    HttpResult runTextSpider(List<String> keywords);

    HttpResult runCommentSpider(String textId);
}
