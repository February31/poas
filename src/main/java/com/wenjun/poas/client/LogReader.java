package com.wenjun.poas.client;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/5/11
 */
@Component
public class LogReader {
    public List<String> read(String fileName) {
        List<String> list = new ArrayList<>();
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String temp;
            while ((temp = br.readLine()) != null) {
                list.add(temp);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return list;
    }
}
