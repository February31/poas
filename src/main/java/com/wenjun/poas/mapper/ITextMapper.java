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

    List<Text> findByEvent(Integer eventId);

    List<Text> findByTime(@Param("time") String time, @Param("eventId") Integer eventId);

    List<Text> findByNotHandled(Integer eventId);

    void deleteText(String textId);

    void updateTextEmotion(Text text);

    void updateText(Text text);
}
