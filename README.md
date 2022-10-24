# 数据库应用接口 Database Interface Appliance

Repo of FDU-SS-DBDesign Lab1 复旦大学数据库设计Lab1

![](https://img.shields.io/badge/JDK-1.8%2B-orange) ![](https://img.shields.io/badge/Maven-3.8.1-green) ![](https://img.shields.io/badge/mysql-v8.0.1-blue)

## 开发与运行环境
使用jdk-1.8开发，数据库运行于mysql v8.0.1。

## 运行说明
在运行时，请按照程序要求分别输入输入数据库的名称、用户名与密码。

若数据库没有room与student这两张表，程序会自动创建。
（对应的初始化SQL语句在文件夹中的room.sql与student.sql文件中）

## 运行原理
首先调用javacsv的库函数读取csv中的数据，并动态生成相应SQL语句，通过JDBC调用mysql的驱动程序进行执行。

## 如果外部数据（原始数据表）数据不完整（例如某个不应该为空的字段缺失数据）或不一致（例如本应有外键关系的数据并没有保持引用完整性），有哪些方法可以处理？

## 处理原始数据的原则
