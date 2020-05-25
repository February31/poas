package com.wenjun.poas.mapper;

import com.sun.javafx.tk.TKClipboard;
import com.wenjun.poas.entity.TextKeyword;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/4/16
 */
@Mapper
public interface ITextKeywordMapper {
    void insert(TextKeyword textKeyword);

    /**
     * 通过事件来查询跟该事件相关的舆情关键词
     *
     * @param event 事件id
     * @return 对象集合
     */
    List<TextKeyword> findByEvent(String event);
}
