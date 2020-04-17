package com.wenjun.poas.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.wenjun.poas.entity.Event;
import com.wenjun.poas.entity.Text;
import com.wenjun.poas.mapper.IEventMapper;
import com.wenjun.poas.mapper.ITextMapper;
import com.wenjun.poas.service.ITextService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/13
 */
@Service
public class TextService implements ITextService {
    @Resource
    ITextMapper textMapper;
    @Resource
    IEventMapper eventMapper;

    @Override
    public List<Text> getText(Integer eventId) {
        return textMapper.findByEvent(eventId);
    }

    @Override
    public List<Text> getText(String time, Integer eventId) {
        return textMapper.findByTime(time, eventId);
    }

    @Override
    public List<Text> getByNotHandled(String event) {
        Integer id;
        if (!StringUtils.isNumber(event)){
            Event e = eventMapper.findByName(event);
            id = e.getId();
        }else {
            id = Integer.parseInt(event);
        }
        return textMapper.findByNotHandled(id);
    }

    @Override
    public void deleteText(String textId) {
        textMapper.deleteText(textId);
    }

    @Override
    public void insertText(Text text) {
        textMapper.insertText(text);
    }

    @Override
    public void updateTextEmotion(Text text) {
        textMapper.updateTextEmotion(text);
    }

    @Override
    public void updateText(Text text) {
        textMapper.updateText(text);
    }
}
