package com.wenjun.poas.config.spider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 关于爬虫的一些配置信息公共类
 *
 * @author xuwenjun
 * @date 2020/4/9
 */
@Configuration
public class SpiderConfig {
    @Value("${spider.project}")
    public String projectName;
    @Value("${spider.weibospider}")
    public String textSpider;
    @Value("${spider.commentspider}")
    public String commentSpider;
    @Value("${spider.run}")
    public String runSpiderUrl;
    @Value("${spider.cancel}")
    public String cancelSpiderUrl;
    @Value("${spider.listjobs}")
    public String listJobsUrl;

//    定时爬取间隔时间
    @Value("${spider.intervalTime}")
    public Integer intervalTime;
//    每次程序启动的执行延迟
    @Value("${spider.initialDelay}")
    public Integer initialDelay;
}
