package com.wenjun.poas.nlp;

/**
 * @author xuwenjun
 * @date 2020/3/31
 */
import com.hankcs.hanlp.HanLP;

/**
 * 第一个Demo，惊鸿一瞥
 *
 * @author hankcs
 */
public class DemoAtFirstSight
{
    public static void main(String[] args)
    {
        System.out.println("首次编译运行时，HanLP会自动构建词典缓存，请稍候……");
        HanLP.Config.enableDebug();         // 为了避免你等得无聊，开启调试模式说点什么:-)
        System.out.println(HanLP.segment("你好，欢迎使用HanLP汉语处理包！接下来请从其他Demo中体验HanLP丰富的功能~"));
    }
}
