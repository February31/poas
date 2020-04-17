package com.wenjun.poas.nlp;

import com.hankcs.hanlp.summary.TextRankKeyword;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 关键词提取器
 * @author xuwenjun
 * @date 2020/4/13
 */
@Component
public class KeywordExtractor {
    TextRankKeyword textRankKeyword = new TextRankKeyword();
    public List<String> extract(String text,Integer size){
        return textRankKeyword.getKeywords(text,size);
    }
}
