package com.wenjun.poas.service;

import com.wenjun.poas.entity.Comment;

import java.util.List;

/**
 * 提供对comment操作的service
 * @author xuwenjun
 * @date 2020/4/13
 */
public interface ICommentService {
    /**
     * @param articleId 微博id
     * @return 评论列表
     */
    List<Comment> getComment(String articleId);

    void insertComment(Comment comment);

    void deleteComment(String commentId);

    void updateCommentAttitude(Comment comment);
}
