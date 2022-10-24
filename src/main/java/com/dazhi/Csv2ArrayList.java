package com.dazhi;

import com.csvreader.CsvReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Csv2ArrayList {
    public static ArrayList<String[]> read(String filename) throws IOException {
        ArrayList<String[]> csvFileList = new ArrayList<>();
        // CSV opened
        CsvReader reader;
        if (filename.equals("room")) {
            reader = new CsvReader(filename+".csv", ',', Charset.forName("GBK"));
        } else {
            reader = new CsvReader(filename+".csv", ',', StandardCharsets.UTF_8);
        }
        reader.readHeaders(); // 跳过表头
        while (reader.readRecord()) {
            // 此处可以根据需求，去除含有部分数据为空的数据行并报错。
//            String[] row = reader.getValues();
//            Boolean flag = true;
//            for (String str:row) {
//                if (str.replaceAll("\\s*","").equals("")){
//                    flag = false;
//                    System.out.println("该行数据有部分为空，已去除。");
//                    break;
//                }
//            }
//            if (flag)
//                csvFileList.add(reader.getValues());
            csvFileList.add(reader.getValues());
        }
        reader.close();
        return csvFileList;
        // CSV closed
    }
}
