package com.dazhi;

import com.dazhi.DBUtil.DBUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void room() {
        try {
            ArrayList<String[]> roomCsvFileList = csv2ArrayList.read("room");
            writeRoomSql(roomCsvFileList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void student() {
        try {
            ArrayList<String[]> studentCsvFileList = csv2ArrayList.read("student");
            writeStudentSql(studentCsvFileList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void writeRoomSql(ArrayList<String []> CsvFileList) {
        try {
            // MySQL opened
            Connection conn = DBUtil.conn();//链接到数据库
            Statement state = conn.createStatement();   //容器
            String sql;
            // 遍历读取的CSV文件
            for (String[] strings : CsvFileList) {
                // 取得第row行第0列的数据
                String kaoDian = strings[0];
                String kaoChang = strings[1];
                String changCi = strings[2];
                String kdname = strings[3];
                String exptime = strings[4];
                String papername = strings[5];
                int kdno = Integer.parseInt(kaoDian);
                int kcno = Integer.parseInt(kaoChang);
                int ccno = Integer.parseInt(changCi);
                // SQL语句
                sql = "insert into room (kdno, kcno, ccno, kdname, exptime, papername)  values  (" + kdno + "," + kcno + "," + ccno + ",'" + kdname + "','" + exptime + "','" + papername + "');";
                // System.out.println(sql);
                try {
                    state.executeUpdate(sql);
                } catch (Exception ex) {
                    System.out.println(kdname+" 考点"+kdno+" 考场"+kcno+" 场次"+ccno+" 信息已入库！");
                }
            }
            conn.close();
            // MySQL closed
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void writeStudentSql(ArrayList<String []> CsvFileList) {
        try {
            // MySQL opened
            Connection conn = DBUtil.conn();//链接到数据库
            Statement state = conn.createStatement();   //容器
            String sql;
            // 遍历读取的CSV文件
            for (String[] strings : CsvFileList) {
                // 取得第row行第0列的数据
                int registno = Integer.parseInt(strings[0]);
                String name = strings[1];
                int kdno = Integer.parseInt(strings[2]);
                int kcno = Integer.parseInt(strings[3]);
                int ccno = Integer.parseInt(strings[4]);
                int seat = Integer.parseInt(strings[5]);
                // SQL语句
                sql = "insert into student (registno, name, kdno, kcno, ccno, seat)  values  (" + registno + ",'" + name + "'," + kdno + "," + kcno + "," + ccno + "," + seat + ");";
                // System.out.println(sql);
                try {
                    state.executeUpdate(sql);
                } catch (Exception ex) {
                    System.out.println("考生"+name+"（考号为"+registno+"）信息已入库！");
                }

            }
            conn.close();
            // MySQL closed
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args)
    {
        DBUtil.init();
        room();
        student();
    }

}

