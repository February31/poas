package com.wenjun.poas.nlp;

import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author xuwenjun
 * @date 2020/4/13
 */
@Component
public class SimilarityAnalyzer {
    DocVectorModel docVectorModel;

    public SimilarityAnalyzer() {
        String filePath = "D:\\hanlp\\hanlp-wiki-vec-zh\\hanlp-wiki-vec-zh.txt";
        WordVectorModel wordVectorModel;
        wordVectorModel = null;
        try {
            wordVectorModel = new WordVectorModel(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        docVectorModel = new DocVectorModel(wordVectorModel);
    }

    public Float analyse(String what, String with) {
        return docVectorModel.similarity(what, with);
    }
}
