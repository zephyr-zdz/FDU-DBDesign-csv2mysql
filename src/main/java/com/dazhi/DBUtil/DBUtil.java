package com.dazhi.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private static final String jdbc = "jdbc:mysql://127.0.0.1:3306/db";
    private static final String password = "12345678";

    public static void init() {
        try {
            Connection conn = DriverManager.getConnection(jdbc, "root", password);
            Statement state = conn.createStatement();   //容器
            state.executeUpdate("CREATE TABLE IF NOT EXISTS room\n" +
                    "(\n" +
                    "    kdno      int          not null,\n" +
                    "    kcno      int          not null,\n" +
                    "    ccno      int          not null,\n" +
                    "    kdname    varchar(128) not null,\n" +
                    "    exptime   varchar(128) not null,\n" +
                    "    papername varchar(128) null,\n" +
                    "    primary key (kdno, kcno, ccno)\n" +
                    ");\n");
            state.executeUpdate("CREATE TABLE IF NOT EXISTS student\n" +
                    "(\n" +
                    "    registno int         not null,\n" +
                    "    name     varchar(10) not null,\n" +
                    "    kdno     int         not null,\n" +
                    "    kcno     int         not null,\n" +
                    "    ccno     int         not null,\n" +
                    "    seat     int         not null,\n" +
                    "    constraint student_pk\n" +
                    "        primary key (registno)\n" +
                    ");");
            conn.close();
            System.out.println("数据库已初始化！");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public static Connection conn() throws SQLException {
        return DriverManager.getConnection(jdbc, "root", password);
    }
}
