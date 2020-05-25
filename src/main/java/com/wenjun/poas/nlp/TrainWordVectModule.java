package com.wenjun.poas.nlp;

import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author xuwenjun
 * @date 2020/4/11
 */
public class TrainWordVectModule {
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\hanlp\\hanlp-wiki-vec-zh\\hanlp-wiki-vec-zh.txt";
//        加载训练好的词向量模型
        WordVectorModel wordVectorModel = new WordVectorModel(filePath);
//        输出词向量
        System.out.println(Arrays.toString(wordVectorModel.query("西瓜").getElementArray()));
//        生成文章向量模型
        DocVectorModel docVectorModel = new DocVectorModel(wordVectorModel);
        System.out.println(docVectorModel.similarity("我喜欢吃西瓜", "我喜欢吃西瓜"));
        System.out.println(docVectorModel.similarity("我喜欢吃西瓜", "我喜欢吃苹果"));
        System.out.println(docVectorModel.similarity("我喜欢吃西瓜", "我吃西瓜"));
        System.out.println(docVectorModel.similarity("我喜欢吃西瓜", "明天会下雨"));
    }
}
