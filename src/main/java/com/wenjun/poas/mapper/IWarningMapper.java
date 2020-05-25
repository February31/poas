package com.wenjun.poas.mapper;

import com.wenjun.poas.entity.Warning;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/5/4
 */
@Mapper
public interface IWarningMapper {
    void insertWarning(Warning warning);

    void deleteWarning(Warning warning);

    Warning findWarning(String eventId);

    void updateTimeAndSize(Warning warning);

    void updateWarning(Warning warning);

    List<Warning> findByUser(String userId);

    List<Warning> findByUserAll(String userId);

    void handledWarning(String eventId);
}
