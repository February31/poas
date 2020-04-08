package com.wenjun.poas.nlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.utility.Predefine;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * @author xuwenjun
 * @date 2020/4/4
 */
public class Hello {
    public static void main(String[] args) throws IOException {
        FileWriter fwriter = null;
        String fileRootPath = "D:\\hanlp\\情感分析\\负向\\";
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\hanlp\\情感分析\\负向\\neg60000.txt"),"GBK");
            BufferedReader br = new BufferedReader(isr);

            StringBuffer sb = new StringBuffer();
            String temp = null;
            int i=1;
            while((temp = br.readLine()) != null){
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
}
