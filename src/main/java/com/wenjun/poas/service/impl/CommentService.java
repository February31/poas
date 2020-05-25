package com.wenjun.poas.service.impl;

import com.wenjun.poas.entity.Comment;
import com.wenjun.poas.entity.PieChartData;
import com.wenjun.poas.mapper.HandlingStatusMapper;
import com.wenjun.poas.mapper.ICommentMapper;
import com.wenjun.poas.service.ICommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/13
 */
@Service
public class CommentService implements ICommentService {
    @Resource
    ICommentMapper commentMapper;
    @Resource
    HandlingStatusMapper handlingStatusMapper;

    @Override
    public List<Comment> getComment(String articleId) {
        return commentMapper.findByTextId(articleId);
    }

    @Override
    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentMapper.deleteComment(comment);
    }

    @Override
    public void updateCommentAttitude(Comment comment) {
        if ("正向".equals(comment.getAttitude())) {
            comment.setAttitude("负向");
        } else {
            comment.setAttitude("正向");
        }
        commentMapper.updateCommentAttitude(comment);
    }

    @Override
    public List<PieChartData> getPieChartData(String weiboId) {
        PieChartData pos = new PieChartData();
        pos.setName("正向");
        pos.setValue(commentMapper.getPosCount(weiboId));
        PieChartData neg = new PieChartData();
        neg.setName("负向");
        neg.setValue(commentMapper.getNegCount(weiboId));
        List<PieChartData> list = new ArrayList<>();
        list.add(pos);
        list.add(neg);
        return list;
    }

    @Override
    public Boolean checkSpider(String weiboId) {
        return handlingStatusMapper.checkCommentSpider(weiboId) == 0;
    }

    @Override
    public Boolean checkNlp(String weiboId) {
        return handlingStatusMapper.checkCommentNlp(weiboId) == 0;
    }
}
