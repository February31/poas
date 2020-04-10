package com.wenjun.poas.client;

import com.wenjun.poas.config.spider.SpiderConfig;
import com.wenjun.poas.entity.HttpResult;
import com.wenjun.poas.util.HttpClientUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuwenjun
 * @date 2020/4/9
 */
@Component
public class SpiderClient {
    @Resource
    SpiderConfig spiderConfig;

    public boolean runSpider(List<String> keywords){
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String url = spiderConfig.runSpiderUrl;
        System.out.println("spiderConfig.runSpiderUrl"+url);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("project", spiderConfig.projectName);
        map.put("spider", "baidu");
        map.put("keyword", 1043);
        HttpResult httpResult = null;
        try {
            httpResult = httpClientUtil.doPost(url, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(httpResult.getCode());
        System.out.println(httpResult.getBody());
        return true;
    }
}
