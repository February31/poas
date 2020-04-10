package com.wenjun.poas.service.impl;

import com.wenjun.poas.client.SpiderClient;
import com.wenjun.poas.service.ISpiderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/9
 */
@Service
public class SpiderService implements ISpiderService {
    @Resource
    SpiderClient spiderClient;

    @Override
    public boolean runSpider(List<String> keywords) {
        if (keywords.size() < 0) {
            return false;
        }
        return spiderClient.runSpider(keywords);
    }
}
