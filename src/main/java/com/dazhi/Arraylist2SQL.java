package com.dazhi;

import com.dazhi.DBUtil.DBUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Arraylist2SQL {
    public static ArrayList<String> getDatabaseColumn(String type) {
        ArrayList<String> columnList = new ArrayList<>();
        try {
            Connection conn = DBUtil.conn();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet columns = metaData.getColumns(null, null,type, null);
            while (columns.next()) {
                columnList.add(columns.getString("COLUMN_NAME"));
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return columnList;
    }
    private static String createSql(String type) {
        // insert into room (
        StringBuilder sql = new StringBuilder("insert into " + type + " (");
        for (String column : getDatabaseColumn(type)) {
            sql.append(column).append(",");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        // insert into room (title 1, title 2, ..., title n
        sql.append(") values (");
        // insert into room (title 1, title 2, ..., title n) values (
        for (String ignored : getDatabaseColumn(type)) {
            sql.append("?,");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        sql.append(");");
        // insert into room (title 1, title 2, ..., title n) values (?,?,...,?);
        System.out.println(sql);
        return sql.toString();
    }
    public static void executeSql(String type, ArrayList<String[]> CsvFileList) {
        try {
            // MySQL opened
            String sql = createSql(type);
            Connection conn = DBUtil.conn(); //链接到数据库
            // 读取表格动态生成pre-statement
            // 遍历读取的CSV文件
            for (String[] strings : CsvFileList) {
                // 取得第row行第0列的数据
                // SQL语句
                PreparedStatement state = conn.prepareStatement(sql); //容器
                int i = 0;
                for (String str : strings) {
                    i++;
                    state.setObject(i,str);
                }
                try {
                    state.executeUpdate();
                } catch (Exception ex) {
                    System.out.println("该条信息重复录入！");
                }
            }
            conn.close();
            // MySQL closed
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


}
