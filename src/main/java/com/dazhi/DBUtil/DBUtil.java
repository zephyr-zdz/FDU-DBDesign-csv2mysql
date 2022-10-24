package com.dazhi.DBUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class DBUtil {
    private static String DBurl = "";
    private static String username = "";
    private static String password = "";

    public static void init() {
        getDBConf();
        try {
            Connection conn = DriverManager.getConnection(DBurl, username, password);
            Statement state = conn.createStatement();   //容器

            Path roomSQLPath = Paths.get("room.sql");
            String roomInitSQL = new String(Files.readAllBytes(roomSQLPath));
            state.executeUpdate(roomInitSQL);

            Path studentSQLPath = Paths.get("student.sql");
            String studentInitSQL = new String(Files.readAllBytes(studentSQLPath));
            state.executeUpdate(studentInitSQL);

            conn.close();
            System.out.println("数据库已创建！");
        } catch (Exception ex) {
            System.out.println("数据库配置信息错误！："+ex.getMessage());
            System.exit (1);
        }
    }

    private static void getDBConf() {
        try {
            InputStream in = Files.newInputStream(Paths.get("conf.properties"));
            ResourceBundle conf = new PropertyResourceBundle(in);
            DBurl = conf.getString("datasource.url");
            username = conf.getString("datasource.username");
            password = conf.getString("datasource.password");
        } catch(Exception exception) {
            System.out.println("数据库配置信息错误！");
        }
    }


    public static Connection conn() {
        try {
            return DriverManager.getConnection(DBurl, username, password);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
