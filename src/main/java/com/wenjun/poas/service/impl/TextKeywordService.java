package com.wenjun.poas.service.impl;

import com.wenjun.poas.entity.TextKeyword;
import com.wenjun.poas.mapper.ITextKeywordMapper;
import com.wenjun.poas.service.ITextKeywordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/16
 */
@Service
public class TextKeywordService implements ITextKeywordService {
    @Resource
    ITextKeywordMapper textKeywordMapper;

    @Override
    public void insert(TextKeyword textKeyword) {
        textKeywordMapper.insert(textKeyword);
    }

    @Override
    public List<TextKeyword> findByEvent(Integer event) {
        return textKeywordMapper.findByEvent(event);
    }
}
