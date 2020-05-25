package com.wenjun.poas.entity;

import lombok.Data;

/**
 * 微博评论
 *
 * @author xuwenjun
 * @date 2020/4/12
 */
@Data
public class Comment {
    private String weiboId;
    private String commentId;
    private String userId;
    private String text;
    private String attitude;
}
