package com.wenjun.poas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * weibo text
 * @author xuwenjun
 * @date 2020/4/12
 */


@Data
public class Text {
    Integer eventId;
    String weiboId;
    String userId;
    Integer commentsCount;
    Integer attitudesCount;
    Integer repostsCount;
    String createdAt;
    String attitude;
    Integer similarity;
    String handledAt;
    String crawledAt;
    String text;

}
