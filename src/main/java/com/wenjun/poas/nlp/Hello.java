package com.wenjun.poas.nlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.utility.Predefine;

import java.io.*;
import java.text.Format;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuwenjun
 * @date 2020/4/4
 */
public class Hello {
    public static void main(String[] args) throws IOException {
        FileWriter fwriter = null;
//        String fileRootPath = "D:\\hanlp\\情感分析\\负向\\";
        String fileRootPath = "D:\\hanlp\\语料库\\aaa\\正向\\";
        try {
//            InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\hanlp\\情感分析\\负向\\neg60000.txt"),"GBK");
            InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\hanlp\\语料库\\pos60000.txt"),"GBK");
            BufferedReader br = new BufferedReader(isr);

            String temp = null;
            int i=1;
            while((temp = br.readLine()) != null){
//                处理一下语料库内容
//                1.去掉#号夹着的
//                2.去掉网址
//                3.去掉@加人名//@xx:
                temp = format(temp);
                String filePath = fileRootPath+i+".txt";
                i++;
                try {
                    fwriter = new FileWriter(filePath);
                    fwriter.write(temp);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        fwriter.flush();
                        fwriter.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static String format(String content) {
        String aa = "";
//        去掉网址
        String url = "[a-zA-z]+://[^\\s]*";
        Pattern p = Pattern.compile(url);
        Matcher m = p.matcher(content);
        content = m.replaceAll(aa).trim();
//        去掉#
        String jinghao = "\\#.*?\\#";
        Pattern p1 = Pattern.compile(jinghao);
        Matcher m1 = p1.matcher(content);
        content = m1.replaceAll(aa).trim();
//        去掉/和：中间的
        String name = "\\/.*?\\:";
        Pattern p2 = Pattern.compile(name);
        Matcher m2 = p2.matcher(content);
        content = m2.replaceAll(aa).trim();
        return content;
    }

//    public static void main(String[] args) {
//        String a = "艾弗森的是发#大师傅发动#发发生芙//@来福小院流浪动物救助:[泪]怎么能这样！https://zhidao.baidu.com/question/562137019.html";
//        a=format(a);
//        System.out.println(a);
//    }
}
