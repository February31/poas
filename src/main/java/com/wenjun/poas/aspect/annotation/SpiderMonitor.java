package com.wenjun.poas.aspect.annotation;

import java.lang.annotation.*;

/**
 * 爬虫监控器注解
 * @author xuwenjun
 * @date 2020/4/15
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpiderMonitor {
    String description()  default "爬虫监控器";
}
