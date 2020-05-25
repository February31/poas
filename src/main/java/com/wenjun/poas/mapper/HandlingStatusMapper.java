package com.wenjun.poas.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用来查询爬虫状态和nlp处理状态的mapper
 *
 * @author xuwenjun
 * @date 2020/4/28
 */
@Mapper
public interface HandlingStatusMapper {
    void startEventSpider(String id);

    void startCommentSpider(String id);

    void finishEventSpider(String id);

    void finishCommentSpider(String id);

    void startEventNlp(String id);

    void startCommentNlp(String id);

    void finishEventNlp(String id);

    void finishCommentNlp(String id);

    Integer checkEventSpider(String id);

    Integer checkEventNlp(String id);

    Integer checkCommentNlp(String id);

    Integer checkCommentSpider(String id);
}
