package com.wenjun.poas.service;

import com.wenjun.poas.entity.Comment;
import com.wenjun.poas.entity.PieChartData;

import java.util.List;

/**
 * 提供对comment操作的service
 *
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

    void deleteComment(Comment comment);

    void updateCommentAttitude(Comment comment);

    List<PieChartData> getPieChartData(String weiboId);

    /**
     * @param weiboId 微博id
     * @return true：爬虫运行完，false反之
     */
    Boolean checkSpider(String weiboId);

    /**
     * @param weiboId 微博id
     * @return true：nlp分析完，false反之
     */
    Boolean checkNlp(String weiboId);
}
