package com.wenjun.poas.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.wenjun.poas.aspect.annotation.SpiderMonitor;
import com.wenjun.poas.client.SpiderClient;
import com.wenjun.poas.entity.Event;
import com.wenjun.poas.entity.HttpResult;
import com.wenjun.poas.mapper.IEventMapper;
import com.wenjun.poas.service.ISpiderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**跟爬虫相关的service
 * @author xuwenjun
 * @date 2020/4/9
 */
@Service
public class SpiderService implements ISpiderService {
    @Resource
    SpiderClient spiderClient;
    @Resource
    IEventMapper eventMapper;

    @SpiderMonitor
    @Override
    public HttpResult runTextSpider(String keywords,String event) {
//        TODO 这里还要再开一个定时器，每两个小时执行一次爬虫。
        String id;
        if (!StringUtils.isNumber(event)){
            Event e = eventMapper.findByName(event);
            id = e.getId().toString();
        }else {
            id = event;
        }
        Boolean running = spiderClient.runTextSpider(keywords,id);
        HttpResult result = new HttpResult();
        result.setBody(running.toString());
        return result;
    }

    @SpiderMonitor
    @Override
    public HttpResult runCommentSpider(String textId) {
        Boolean running = spiderClient.runCommentSpider(textId);
        HttpResult result = new HttpResult();
        result.setBody(running.toString());
        return result;
    }
}
