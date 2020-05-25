package com.wenjun.poas.service;

import com.wenjun.poas.entity.Comment;
import com.wenjun.poas.entity.Text;

import java.util.List;

/**
 * 用来做自然语言处理的service
 *
 * @author xuwenjun
 * @date 2020/4/12
 */
public interface INlpService {
    /**
     * 情感分析
     *
     * @param texts 要被分析的句子集
     */
    void sentimentAnalysisText(List<Text> texts);

    /**
     * 情感分析
     *
     * @param comments 要被分析的句子集
     */
    void sentimentAnalysisComment(List<Comment> comments);

    /**
     * 相似数分析
     *
     * @param texts 句子
     */
    void similarityAnalysis(List<Text> texts);

    /**
     * 提取关键词
     *
     * @param texts 句子集
     */
    void extractKeywords(List<Text> texts);

    void dateFormat(List<Text> texts);
}
