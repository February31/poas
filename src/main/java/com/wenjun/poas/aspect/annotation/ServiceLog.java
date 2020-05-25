package com.wenjun.poas.aspect.annotation;

import java.lang.annotation.*;

/**
 * 业务日志监控注解
 * @author xuwenjun
 * @date 2020/5/5
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceLog {
    String description()  default "业务日志监控";
}
