package com.wenjun.poas.service.impl;

import com.wenjun.poas.entity.Comment;
import com.wenjun.poas.entity.Text;
import com.wenjun.poas.entity.TextKeyword;
import com.wenjun.poas.nlp.Classifier;
import com.wenjun.poas.nlp.KeywordExtractor;
import com.wenjun.poas.nlp.SimilarityAnalyzer;
import com.wenjun.poas.service.INlpService;
import com.wenjun.poas.service.ITextKeywordService;
import com.wenjun.poas.util.DateFormatUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuwenjun
 * @date 2020/4/13
 */
@Service
public class NlpService implements INlpService {

    @Resource
    Classifier classifier;
    @Resource
    SimilarityAnalyzer analyzer;
    @Resource
    KeywordExtractor keywordExtractor;
    @Resource
    DateFormatUtil dateFormatUtil;
    @Resource
    ITextKeywordService textKeywordService;

    @Override
    public void sentimentAnalysisText(List<Text> texts) {
        if (texts.size() == 0) {
            return;
        }
        for (Text text : texts) {
            text.setAttitude(classifier.predict(text.getText()));
        }
    }

    @Override
    public void sentimentAnalysisComment(List<Comment> comments) {
        if (comments.size() == 0) {
            return;
        }
        for (Comment comment : comments) {
            comment.setAttitude(classifier.predict(comment.getText()));
        }
    }

    /**
     * 1. 分析出相似度，写进数据库
     */
    @Override
    public void similarityAnalysis(List<Text> texts) {
//
//        2.接着分析第二个，先去查之前的元素。比如第二个去查第一个的相似，
//        如果没有自己的下标，就一定不相似。
//        3.如果前面没找到相似的，下标越过自己后，开始分析接下来的。
//        首先看接下来的下标是否和之前的相似，相似直接排除，不相似则计算。
//        如果前面找到了相似的，就把他的相似下标拿过来算一下，其他不用算。
        List<List<Integer>> similarityList = new ArrayList<>(texts.size());

        for (int i = 0; i < texts.size(); i++) {
            List<Integer> list = new ArrayList<>();
//          1.对第一个进行全面分析，将与他相似的下标记下来。
            if (i == 0) {
                for (int j = i + 1; j < texts.size(); j++) {
                    if (similarity(texts.get(i).getText(), texts.get(j).getText())) {
                        list.add(j);
                    }
                }
//          2.除第一个以外的用算法
            } else {
                boolean finished = false;
//              先看前面是否有跟他相似的
                for (int j = 0; j < i; j++) {
//                  前面有跟这个相似的，那么就只需要算一下前面与这个相似的舆情的相似舆情是否与现在的相似就可以了。
                    if (similarityList.get(j).contains(i)) {
                        for (Integer re : similarityList.get(j)) {
//                          如果前面的明确与自己相似，则把前面的下标加进自己的相似集合中
                            if (re == i) {
                                list.add(j);
//                          相似舆情的相似舆情，需要计算一下，合适才加入自己的相似集合中
                            } else {
                                if (similarity(texts.get(re).getText(), texts.get(i).getText())) {
                                    list.add(j);
                                }
                            }

                        }
                        finished = true;
                    }
                }
                //前面没有跟他相似的，则算后面的
                if (!finished) {
                    for (int j = i + 1; j < texts.size(); j++) {
                        //如果后头的（j）跟前面的(小于i的)相似，则不用算。
                        boolean analysis = true;
                        for (int k = 0; k < i; k++) {
                            if (similarityList.get(k).contains(j)) {
                                analysis = false;
                                break;
                            }
                        }
                        if (!analysis) {
                            continue;
                        }
//                      后面的跟前面的不相似，才计算
                        if (similarity(texts.get(j).getText(), texts.get(i).getText())) {
                            list.add(j);
                        }
                    }
                }

            }
            similarityList.add(list);
        }

    }

    private Boolean similarity(String s1, String s2) {
        float similarity = analyzer.analyse(s1, s2);
        return similarity >= 0.8;
    }

    /**
     * 1.分析出关键词，写进数据库
     */
    @Override
    public void extractKeywords(List<Text> texts) {
        if (texts.size() == 0) {
            return;
        }
        for (Text text : texts) {
            String content = format(text.getText());
            List<String> lists = keywordExtractor.extract(content, 8);
            StringBuilder keywords = new StringBuilder();
            for (String list : lists) {
                keywords.append(list);
                keywords.append(" ");
            }
            TextKeyword textKeyword = new TextKeyword();
            textKeyword.setEventId(text.getEventId());
            textKeyword.setTextId(text.getWeiboId());
            textKeyword.setKeywords(keywords.toString());
            textKeywordService.insert(textKeyword);
        }
    }

    private String format(String content) {
//        去掉“#”这种特殊符号
        String regEx = "[\n`~!@#$%^&*()+=|{}':;,\\[\\].<>/?！￥…（）—【】‘；：”“’。， 、？]";
        String aa = "";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(content);
        return m.replaceAll(aa).trim();
    }

    /**
     * 分析出发布时间，便于分析时间趋势
     */
    @Override
    public void dateFormat(List<Text> texts) {
        if (texts.size() == 0) {
            return;
        }
        for (Text text : texts) {
            text.setCreatedAt(dateFormatUtil.getCreatedTime(text.getCreatedAt(), text.getCrawledAt()));
            text.setHandledAt(dateFormatUtil.getHandledTime(text.getCrawledAt()));
        }
    }
}
