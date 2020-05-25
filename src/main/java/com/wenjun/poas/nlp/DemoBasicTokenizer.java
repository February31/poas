package com.wenjun.poas.nlp;

/**
 * @author xuwenjun
 * @date 2020/3/31
 */
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.tokenizer.*;

import java.io.IOException;

/**
 * 演示基础分词，基础分词只进行基本NGram分词，不识别命名实体，不使用用户词典
 *
 * @author hankcs
 */
public class DemoBasicTokenizer
{
    public static void main(String[] args) throws IOException {
        String text = "商品和服务";
        System.out.println(BasicTokenizer.segment(text));
        // 测试分词速度
        long start = System.currentTimeMillis();
        int pressure = 100000;
        for (int i = 0; i < pressure; ++i)
        {
            BasicTokenizer.segment(text);
        }
        double costTime = (System.currentTimeMillis() - start) / (double) 1000;
        System.out.printf("BasicTokenizer分词速度：%.2f字每秒\n", text.length() * pressure / costTime);
    }
}
