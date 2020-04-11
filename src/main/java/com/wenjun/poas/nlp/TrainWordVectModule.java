package com.wenjun.poas.nlp;

import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;

import java.io.IOException;

/**
 * @author xuwenjun
 * @date 2020/4/11
 */
public class TrainWordVectModule {
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\hanlp\\hanlp-wiki-vec-zh\\hanlp-wiki-vec-zh.txt";
        WordVectorModel wordVectorModel = new WordVectorModel(filePath);
        DocVectorModel docVectorModel = new DocVectorModel(wordVectorModel);
        System.out.println(docVectorModel.similarity("山西副省长贪污腐败开庭", "山西副省长贪污腐败开庭"));
        System.out.println(docVectorModel.similarity("山西副省长贪污腐败开庭", "股票基金增长"));
    }
}
