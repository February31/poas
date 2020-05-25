package com.wenjun.poas.nlp;

import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.hankcs.hanlp.classification.models.NaiveBayesModel;
import com.hankcs.hanlp.corpus.io.IOUtil;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 贝叶斯分类器
 *
 * @author xuwenjun
 * @date 2020/4/13
 */
@Component
public class Classifier {
    public static final String CORPUS_FOLDER = "D:/hanlp/语料库/情感分析";
    /**
     * 模型保存路径
     */
    public static final String MODEL_PATH = "d:/hanlp/test/classification-model-2.ser";
    IClassifier classifier;

    public Classifier() {
        NaiveBayesModel model = (NaiveBayesModel) IOUtil.readObjectFrom(MODEL_PATH);
        if (model != null) {
            classifier = new NaiveBayesClassifier(model);
        } else {
            File corpusFolder = new File(CORPUS_FOLDER);
            if (!corpusFolder.exists() || !corpusFolder.isDirectory()) {
                System.err.println("没有文本分类语料，请阅读IClassifier.train(java.lang.String)中定义的语料格式与语料下载：" +
                        "https://github.com/hankcs/HanLP/wiki/%E6%96%87%E6%9C%AC%E5%88%86%E7%B1%BB%E4%B8%8E%E6%83%85%E6%84%9F%E5%88%86%E6%9E%90");
//                System.exit(1);
            }
        }

    }

    public String predict(String text) {
        return classifier.classify(text);
    }
}
