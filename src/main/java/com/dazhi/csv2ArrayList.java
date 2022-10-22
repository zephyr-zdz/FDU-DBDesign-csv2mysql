package com.dazhi;

import com.csvreader.CsvReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class csv2ArrayList {
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
            // System.out.println(reader.getRawRecord());
            csvFileList.add(reader.getValues());
        }
        reader.close();
        return csvFileList;
        // CSV closed
    }
}
