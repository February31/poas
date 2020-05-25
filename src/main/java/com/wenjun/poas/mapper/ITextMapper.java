package com.wenjun.poas.mapper;

import com.wenjun.poas.entity.Text;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/12
 */
@Mapper
public interface ITextMapper {
    void insertText(Text text);

    List<Text> findByEvent(String eventId);

    List<Text> findByTime(@Param("time") String time, @Param("eventId") String eventId);

    List<Text> findByNotHandled(String eventId);

    Integer getPosCount(String eventId);

    Integer getNegCount(String eventId);

    List<Text> findByDay(@Param("day") String day, @Param("eventId") String eventId);

    void deleteText(Text text);

    void updateTextEmotion(Text text);

    void updateText(Text text);

    void crawlComment(String weiboId);
}
