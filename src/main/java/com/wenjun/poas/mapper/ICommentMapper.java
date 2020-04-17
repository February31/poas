package com.wenjun.poas.mapper;

import com.wenjun.poas.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/12
 */
@Mapper
public interface ICommentMapper {
    void insertComment(Comment comment);

    List<Comment> findByTextId(String textId);

    void deleteComment(String commentId);

    void updateCommentAttitude(Comment comment);
}
