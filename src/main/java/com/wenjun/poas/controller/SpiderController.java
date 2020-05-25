package com.wenjun.poas.controller;

import com.wenjun.poas.entity.Event;
import com.wenjun.poas.entity.HttpResult;
import com.wenjun.poas.service.ISpiderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 提供访问爬虫组件的接口
 *
 * @author xuwenjun
 * @date 2020/4/9
 */
@Log4j2
@RestController
public class SpiderController {
    @Resource
    ISpiderService spiderService;

    @PostMapping("/spider/crawlText")
    public HttpResult crawl(@RequestHeader("Authorization") String user, @RequestBody Event event) {
        log.info("{} {}", user, event);
        return spiderService.runTextSpider(event);
    }

    @PostMapping("/spider/crawlComment")
    public HttpResult crawl(@RequestHeader("Authorization") String user, @RequestParam String textId) {
        log.info("{} {}", user, textId);
        return spiderService.runCommentSpider(textId);
    }
}
