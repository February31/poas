package com.wenjun.poas.service;

import com.wenjun.poas.entity.TextKeyword;

import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/16
 */
public interface ITextKeywordService {
    void insert(TextKeyword textKeyword);

    List<TextKeyword> findByEvent(Integer event);
}
