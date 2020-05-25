package com.wenjun.poas.service;

import com.wenjun.poas.entity.TextKeyword;
import com.wenjun.poas.entity.WordCloudData;

import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/16
 */
public interface ITextKeywordService {
    void insert(TextKeyword textKeyword);

    List<WordCloudData> findByEvent(String event);
}
