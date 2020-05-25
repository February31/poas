package com.wenjun.poas;

import com.hankcs.hanlp.summary.TextRankKeyword;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xuwenjun
 * @date 2020/5/8
 */
@SpringBootTest
public class NlpTests {
    @Test
    public void exactKeywords(){
        TextRankKeyword textRankKeyword = new TextRankKeyword();
        String s = "PHP是最好的语言";
        System.out.println(textRankKeyword.getKeywords(s, 3));
    }
}
