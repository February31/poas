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
    @Resource
    HttpClientUtil httpClientUtil;

    /**
     * 运行微博正文爬虫
     * @param keywords key words
     * @return is running
     */
    public Boolean runTextSpider(String keywords,String event){
        if ("".equals(keywords)||keywords.length()==0) {
            return false;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("project", spiderConfig.projectName);
        map.put("spider", spiderConfig.textSpider);
        map.put("keyword", "疫情");
        map.put("event",event);
//        map.put("keyword", keywords);
        HttpResult httpResult = null;
        try {
            httpResult = httpClientUtil.doPost(spiderConfig.runSpiderUrl, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(httpResult.getCode());
        System.out.println(httpResult.getBody());
        return true;
    }

    /**
     * 运行微博评论爬虫
     * @param textId weibo text id
     * @return is running
     */
    public Boolean runCommentSpider(String textId){
        Map<String, Object> map = new HashMap<>();
        map.put("project", spiderConfig.projectName);
        map.put("spider", spiderConfig.commentSpider);
        map.put("weibo_id", textId);
        HttpResult httpResult = null;
        try {
            httpResult = httpClientUtil.doPost(spiderConfig.runSpiderUrl, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(httpResult.getCode());
        System.out.println(httpResult.getBody());
        return true;
    }

    /**
     * 查看爬虫工作情况的接口
     * @param projectName project name
     * @return request result
     */
    public HttpResult listJobs(String projectName){
        String url = spiderConfig.listJobsUrl;
        Map<String, Object> map = new HashMap<>();
        map.put("project", projectName);
        HttpResult httpResult = null;
        try {
            httpResult = httpClientUtil.doGet(url, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpResult;
    }

}
