package com.wenjun.poas.nlp;

import java.io.File;
import java.io.IOException;

/**
 * @author xuwenjun
 * @date 2020/4/6
 */
public class Spider {
    public static void main(String[] args) {
        Process proc;
        try {
            // python脚本文件路径
            String pyFilePath = "D:/Pycharm_WorkSpace/weibo/start.py";
            // 传给python的参数
//            String argv1 = "一人之下";
//            + " " +argv1
            String[] commends = {"D:\\Pycharm_WorkSpace\\weibo\\venv\\Scripts\\python.exe",pyFilePath};
            proc = Runtime.getRuntime().exec("python "+pyFilePath);
            int i = proc.waitFor();
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
