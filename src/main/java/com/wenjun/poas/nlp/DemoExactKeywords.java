package com.wenjun.poas.nlp;

import com.hankcs.hanlp.summary.TextRankKeyword;

/**
 * @author xuwenjun
 * @date 2020/5/8
 */
public class DemoExactKeywords {
    public static void main(String[] args) {
        TextRankKeyword textRankKeyword = new TextRankKeyword();
        String s = "PHP是最好的语言吗？";
        System.out.println(textRankKeyword.getKeywords(s, 3));
    }
}
