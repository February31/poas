package com.wenjun.poas.util;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 封装的日期计算工具类
 *
 * @author xuwenjun
 * @date 2020/4/13
 */
@Component
public class DateFormatUtil {

    /**
     * 刚刚
     * 几分钟前
     * <p>
     * 几小时前
     * 昨天 +时间
     * 日期。
     */
    public String getCreatedTime(String created, String crawled) {
        String result;
        long crawledNew = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            crawledNew = format.parse(crawled).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (created.equals("刚刚")) {
            result = crawled;
        } else if (created.contains("分钟")) {
            long minute = Long.parseLong(created.split("分")[0]);
            crawledNew -= 60 * minute * 1000;
            result = format.format(new Date(crawledNew));
        } else if (created.contains("小时")) {
            long hour = Long.parseLong(created.split("小")[0]);
            crawledNew -= 60 * 60 * hour * 1000;
            result = format.format(new Date(crawledNew));
        } else if (created.contains("昨天")) {
            //2020-04-16 16:41
            crawledNew -= 60 * 60 * 24 * 1000;
            //2020-04-15 16:41
            crawled = format.format(new Date(crawledNew));
            //2020-04-15
            crawled = crawled.split(" ")[0];
            //2020-04-15 16:31
            result = created.replace("昨天", crawled);
        } else {
            String month = created.split("-")[0];
            String date = created.split("-")[1];

            String month2 = crawled.substring(5, 7);
            String date2 = crawled.substring(8, 10);

            long day;
            if (month.equals(month2)) {
                day = Integer.parseInt(date2) - Integer.parseInt(date);
            } else {
                day = (Integer.parseInt(month2) - Integer.parseInt(month)) * 30 + Integer.parseInt(date2) - Integer.parseInt(date);
            }
            crawledNew -= 60 * 60 * 24 * 1000 * day;
            result = format.format(new Date(crawledNew));
        }
        return result;
    }

    public String getHandledTime(String crawled) {
        long handled = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        try {
            handled = format.parse(crawled).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format.format(new Date(handled));
    }

}
