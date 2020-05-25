package com.wenjun.poas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * weibo text
 *
 * @author xuwenjun
 * @date 2020/4/12
 */

@Data
public class Text {
    private Integer eventId;
    private String weiboId;
    private String userId;
    private Integer commentsCount;
    private Integer attitudesCount;
    private Integer repostsCount;
    private String createdAt;
    private String attitude;
    private Integer similarity;
    private String handledAt;
    private String crawledAt;
    private String text;
    private String commentStatus;

}
