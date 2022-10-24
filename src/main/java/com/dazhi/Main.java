package com.dazhi;

import com.dazhi.DBUtil.DBUtil;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void csv2db(String type) {
        try {
            ArrayList<String[]> csvFileList = Csv2ArrayList.read(type);
            Arraylist2SQL.executeSql(type, csvFileList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        DBUtil.init();
        csv2db("room");
        csv2db("student");
    }

}

