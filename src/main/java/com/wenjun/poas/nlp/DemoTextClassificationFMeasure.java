package com.wenjun.poas.nlp;

/**
 * @author xuwenjun
 * @date 2020/3/31
 */

import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.hankcs.hanlp.classification.corpus.FileDataSet;
import com.hankcs.hanlp.classification.corpus.IDataSet;
import com.hankcs.hanlp.classification.corpus.MemoryDataSet;
import com.hankcs.hanlp.classification.statistics.evaluations.Evaluator;
import com.hankcs.hanlp.classification.statistics.evaluations.FMeasure;
import com.hankcs.hanlp.classification.tokenizers.BigramTokenizer;
import com.hankcs.hanlp.classification.tokenizers.HanLPTokenizer;

import java.io.IOException;

import static com.wenjun.poas.nlp.DemoTextClassification.CORPUS_FOLDER;

/**
 * 演示了分割训练集和测试集,进行更严谨的测试
 *
 * @author hankcs
 */
public class DemoTextClassificationFMeasure {

    public static void main(String[] args) throws IOException {
        IDataSet trainingCorpus = new FileDataSet().                      // FileDataSet省内存，可加载大规模数据集
                setTokenizer(new BigramTokenizer()).                     // 支持不同的ITokenizer
                load(CORPUS_FOLDER, "UTF-8", 0.8);               // 前80%作为训练集
        IClassifier classifier = new NaiveBayesClassifier();
        classifier.train(trainingCorpus);
        IDataSet testingCorpus = new MemoryDataSet(classifier.getModel()).
                load(CORPUS_FOLDER, "UTF-8", -0.2);        // 后20%作为测试集
        // 计算准确率
        FMeasure result = Evaluator.evaluate(classifier, testingCorpus);
        System.out.println(result);
    }
}
