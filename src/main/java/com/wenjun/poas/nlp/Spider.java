package com.wenjun.poas.nlp;

import com.hankcs.hanlp.HanLP;

/**
 * @author xuwenjun
 * @date 2020/4/6
 */
public class Spider {
    public static void main(String[] args) {
        System.out.println(HanLP.extractKeyword("【受疫情影响一季度航空业亏损400亿 寄望五一恢复】4月15日，" +
                "山东航空和华夏航空率先发布一季度财报预告，两家航司均报亏损。" +
                "其中，山东航空预计亏损5亿至7亿元，华夏航空预计亏损9075.7万元至11586万元。" +
                "民航局新闻发言人、航空安全办公室主任熊杰15日接受媒体采访时透露，因受新冠肺炎疫情影响，一季度全行业累计亏损398.2亿元，" +
                "其中，航空公司亏损336.2亿元。  ",8));
    }
}
