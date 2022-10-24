# 数据库应用接口 Database Interface Appliance

Repo of FDU-SS-DBDesign Lab1 复旦大学数据库设计Lab1

![JDK 1.8+](https://img.shields.io/badge/JDK-1.8%2B-orange)
![Maven 3.8.1](https://img.shields.io/badge/Maven-3.8.1-green)
![mysql v8.0.1](https://img.shields.io/badge/mysql-v8.0.1-blue)

## 开发与运行环境

使用jdk-1.8开发，数据库运行于 mysql v8.0.1。

## 运行说明

在运行时，请先修改文件夹中conf.properties，正确配置数据库url地址、用户名、密码等数据库相关信息。

若数据库没有room与student这两张表，程序会自动创建。
（对应的初始化SQL语句在源代码的room.sql与student.sql文件中）

## 运行原理

首先调用javacsv的库函数读取csv中的数据，并动态生成相应SQL语句，通过JDBC调用mysql的驱动程序进行执行。

由于sql语句是动态生成的，因此该程序并不受表结构的限制。只要csv文件与表的结构相对应，就能将csv文件中的数据导入mysql数据库中。

## 如果外部数据数据不完整或不一致（没有保持引用完整性），有哪些方法可以处理？

可以在读取csv文件时，对数据进行检查是否有字段缺失数据（源代码的注释中写入了相应的执行代码）。数据在导入数据库之前应该检查是否保持了引用完整性，若没有则应该

## 处理原始数据的原则

首先要按照字段要求对数据进行初步的检查，再基于引用完整性检查数据对于外键的
