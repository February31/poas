package com.wenjun.poas.config.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 关于舆情报警的配置
 * @author xuwenjun
 * @date 2020/5/4
 */
@Configuration
public class WarningConfig {
    @Value("${warning.title}")
    public String title;
    @Value("${warning.content}")
    public String content;
    @Value("${warning.content2}")
    public String content2;
    @Value("${warning.content3}")
    public String content3;

}
