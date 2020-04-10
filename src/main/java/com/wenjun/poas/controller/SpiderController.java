package com.wenjun.poas.controller;

import com.wenjun.poas.service.ISpiderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 提供访问爬虫组件的接口
 *
 * @author xuwenjun
 * @date 2020/4/9
 */
@Controller
public class SpiderController {
    @Resource
    ISpiderService spiderService;
    @ResponseBody
    @GetMapping("/crawl")
    public boolean crawl(@RequestBody List<String> keywords) {
        return spiderService.runSpider(keywords);
    }
}
