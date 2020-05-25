package com.wenjun.poas.service.impl;

import com.wenjun.poas.entity.TextKeyword;
import com.wenjun.poas.entity.WordCloudData;
import com.wenjun.poas.mapper.ITextKeywordMapper;
import com.wenjun.poas.service.ITextKeywordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    public List<WordCloudData> findByEvent(String event) {
        List<WordCloudData> res = new ArrayList<>();
        List<TextKeyword> list = textKeywordMapper.findByEvent(event);
        if (list.size() < 1) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
//        把所有的关键词丢进hashmap，key为关键词，value为个数。
        for (TextKeyword textKeyword : list) {
            String[] keywords = textKeyword.getKeywords().split(" ");
            for (String keyword : keywords) {
                if (map.containsKey(keyword)) {
                    map.put(keyword, map.get(keyword) + 1);
                } else {
                    map.put(keyword, 1);
                }
            }
        }
//        sort by the value
        ArrayList<Map.Entry<String, Integer>> arrayList = new ArrayList<>(map.entrySet());
        arrayList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        for (int i = 0; i < 50; i++) {
            WordCloudData wordCloudData = new WordCloudData();
            wordCloudData.setX(arrayList.get(i).getKey());
            wordCloudData.setValue(arrayList.get(i).getValue());
            res.add(wordCloudData);
        }
        return res;
    }

}
