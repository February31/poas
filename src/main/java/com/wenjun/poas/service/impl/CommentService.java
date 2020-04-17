package com.wenjun.poas.service.impl;

import com.wenjun.poas.entity.Comment;
import com.wenjun.poas.mapper.ICommentMapper;
import com.wenjun.poas.service.ICommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/13
 */
@Service
public class CommentService implements ICommentService {
    @Resource
    ICommentMapper commentMapper;
    @Override
    public List<Comment> getComment(String articleId) {
        return commentMapper.findByTextId(articleId);
    }

    @Override
    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    public void deleteComment(String commentId) {
        commentMapper.deleteComment(commentId);
    }

    @Override
    public void updateCommentAttitude(Comment comment) {
        commentMapper.updateCommentAttitude(comment);
    }
}
