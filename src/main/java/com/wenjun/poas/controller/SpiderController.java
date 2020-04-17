package com.wenjun.poas.controller;

import com.wenjun.poas.entity.HttpResult;
import com.wenjun.poas.service.ISpiderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 提供访问爬虫组件的接口
 *
 * @author xuwenjun
 * @date 2020/4/9
 */
@RestController
public class SpiderController {
    @Resource
    ISpiderService spiderService;

    @GetMapping("/crawlText")
    public HttpResult crawl(@RequestParam String keywords, @RequestParam String event) {
        return spiderService.runTextSpider(keywords, event);
    }

    @GetMapping("/crawlComment")
    public HttpResult crawl(@RequestParam String textId) {
        return spiderService.runCommentSpider(textId);
    }
}
