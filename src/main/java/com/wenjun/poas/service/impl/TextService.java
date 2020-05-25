package com.wenjun.poas.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.wenjun.poas.entity.Event;
import com.wenjun.poas.entity.LineChartData;
import com.wenjun.poas.entity.PieChartData;
import com.wenjun.poas.entity.Text;
import com.wenjun.poas.mapper.HandlingStatusMapper;
import com.wenjun.poas.mapper.IEventMapper;
import com.wenjun.poas.mapper.ITextMapper;
import com.wenjun.poas.service.ITextService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Resource
    HandlingStatusMapper handlingStatusMapper;

    @Override
    public List<Text> getText(String eventId) {
        return filter(eventId, textMapper.findByEvent(eventId));
    }

    @Override
    public List<Text> getText(String time, String eventId) {
        return filter(eventId, textMapper.findByTime(time, eventId));
    }

    @Override
    public List<Text> getByNotHandled(String event) {
        String id;
        if (!StringUtils.isNumber(event)) {
            Event e = eventMapper.findByName(event);
            id = e.getId();
        } else {
            id = event;
        }
        return textMapper.findByNotHandled(id);
    }

    @Override
    public List<LineChartData> getByDay(String day, String eventId) {
        List<Text> list = textMapper.findByDay(day, eventId);
        Map<String, Integer> map = new HashMap<>();
//        根据结果的小时分割，放进map中
        for (Text text : list) {
            String create = text.getCreatedAt();
            String hour = create.substring(11, 13);
            if (map.containsKey(hour)) {
                map.put(hour, map.get(hour) + 1);
            } else {
                map.put(hour, 1);
            }
        }
        List<LineChartData> res = new ArrayList<>();
//        初始化列表，把二十四个小时放进去
        for (Integer i = 0; i < 24; i++) {
            LineChartData lineChartData = new LineChartData();
            lineChartData.setTime(i.toString());
            lineChartData.setValue(0);
            res.add(lineChartData);
        }
//        计算出每个小时的数量
        for (String key : map.keySet()) {
            for (LineChartData lineChartData : res) {
                if (Integer.parseInt(key) == Integer.parseInt(lineChartData.getTime())) {
                    lineChartData.setValue(map.get(key));
                }
            }
        }
        for (LineChartData i : res) {
            i.setTime(i.getTime() + "点");
        }
        return res;
    }

    @Override
    public List<PieChartData> getPieChartData(String eventId) {
        PieChartData pos = new PieChartData();
        pos.setName("正向");
        pos.setValue(textMapper.getPosCount(eventId));
        PieChartData neg = new PieChartData();
        neg.setName("负向");
        neg.setValue(textMapper.getNegCount(eventId));
        List<PieChartData> list = new ArrayList<>();
        list.add(pos);
        list.add(neg);
        return list;
    }

    @Override
    public void deleteText(Text text) {
        textMapper.deleteText(text);
    }

    @Override
    public void insertText(Text text) {
        textMapper.insertText(text);
    }

    @Override
    public void updateTextEmotion(Text text) {
        if ("正向".equals(text.getAttitude())) {
            text.setAttitude("负向");
        } else {
            text.setAttitude("正向");
        }
        textMapper.updateTextEmotion(text);
    }

    @Override
    public void updateText(Text text) {
        textMapper.updateText(text);
    }

    @Override
    public Boolean checkSpider(String id) {
        return handlingStatusMapper.checkEventSpider(id) == 0;
    }

    @Override
    public Boolean checkNlp(String id) {
        return handlingStatusMapper.checkEventNlp(id) == 0;
    }

    /**
     * 用前端配置的过滤词组过滤一下爬取到的舆情
     *
     * @param eventId 事件id
     * @param list    舆情列表
     * @return 过滤后的
     */
    private List<Text> filter(String eventId, List<Text> list) {
        String filter = eventMapper.getFilterWords(eventId);
        String time = eventMapper.getFilterTime(eventId);
        if (filter.length() < 1) {
            return list;
        }
        String[] filterWords = filter.split(" ");
        Iterator<Text> it = list.iterator();
        while (it.hasNext()) {
            Text text = it.next();
//            添加过滤规则之后拿到的数据才过滤
            if (!"0000-00-00 00:00".equals(time)) {
                try {
                    if (compare(text.getHandledAt(), time)) {
                        continue;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            for (String s : filterWords) {
                if (text.getText().contains(s)) {
                    it.remove();
                    break;
                }
            }

        }
        return list;
    }

    private Boolean compare(String time1, String time2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date1 = simpleDateFormat.parse(time1);
        Date date2 = simpleDateFormat.parse(time2);
        return date1.before(date2);
    }
}
