package com.wenjun.poas.nlp;

/**
 * @author xuwenjun
 * @date 2020/3/31
 */
import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.hankcs.hanlp.classification.models.NaiveBayesModel;
import com.hankcs.hanlp.corpus.io.IOUtil;

import java.io.File;
import java.io.IOException;

/**
 * 第一个demo,演示文本分类最基本的调用方式
 *
 * @author hankcs
 */
public class DemoTextClassification
{
    /**
     * 搜狗文本分类语料库5个类目，每个类目下1000篇文章，共计5000篇文章
     */
//    public static final String CORPUS_FOLDER = "D:/hanlp/情感分析";
    public static final String CORPUS_FOLDER = "D:/hanlp/ChnSentiCorp情感分析酒店评论";
    /**
     * 模型保存路径
     */
    public static final String MODEL_PATH = "d:/hanlp/test/classification-model-2.ser";


    public static void main(String[] args) throws IOException
    {
        IClassifier classifier = new NaiveBayesClassifier(trainOrLoadModel());
        predict(classifier, "这本书还不错，推荐");
        predict(classifier, "美国真是太垃圾了");
//        predict(classifier, "奔驰太好开了");
//        predict(classifier, "如果真想用食物解压,建议可以食用燕麦");
//        predict(classifier, "通用及其部分竞争对手目前正在考虑解决库存问题");
    }

    private static void predict(IClassifier classifier, String text)
    {
        System.out.printf("《%s》 属于分类 【%s】\n", text, classifier.classify(text));
    }

    private static NaiveBayesModel trainOrLoadModel() throws IOException
    {
        NaiveBayesModel model = (NaiveBayesModel) IOUtil.readObjectFrom(MODEL_PATH);
        if (model != null){
            return model;
        }

        File corpusFolder = new File(CORPUS_FOLDER);
        if (!corpusFolder.exists() || !corpusFolder.isDirectory())
        {
            System.err.println("没有文本分类语料，请阅读IClassifier.train(java.lang.String)中定义的语料格式与语料下载：" +
                    "https://github.com/hankcs/HanLP/wiki/%E6%96%87%E6%9C%AC%E5%88%86%E7%B1%BB%E4%B8%8E%E6%83%85%E6%84%9F%E5%88%86%E6%9E%90");
            System.exit(1);
        }
        // 创建分类器，更高级的功能请参考IClassifier的接口定义
        IClassifier classifier = new NaiveBayesClassifier();
        // 训练后的模型支持持久化，下次就不必训练了
        classifier.train(CORPUS_FOLDER);
        model = (NaiveBayesModel) classifier.getModel();
        IOUtil.saveObjectTo(model, MODEL_PATH);
        return model;
    }
}
