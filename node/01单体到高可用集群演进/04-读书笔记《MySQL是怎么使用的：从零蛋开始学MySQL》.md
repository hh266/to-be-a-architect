## MySQL打卡第一天
1. MySQL 的优势：免费、开源、跨平台、性能强。
2. Window 下 MySQL的安装、环境配置、启动运行。
3. 命令行连接使用 Mysql，了解了Mysql 语句使用的注意事项：
   - 命令要以 ； \g \G 其中之一结尾；
   - 命令可以随意换行；
   - 可以一次提交多个命令；
   - 使用\c放弃本次操作；
   - MySQL默认对命令的大小写并没有限制，但是按照规范，命令、函数什么的都是要大写的，而一些名称类的东西，比如数据库名，表名、列名啥的都是要小写的；
   - 要使用单引号''或者双引号""把字符串内容引起来。

## MySQL打卡第二天

学习了Mysql的数据类型：

1. 整数类型：TINYINT（1）、SMALLINT（2）、MEDIUMINT（3）、INT（4）、BIGINT（8）。一个字节占8位，以 INT 为例，他的无符号数取值范围为 0 ~ 2²⁴-1，有符号数的取值范围为 -2²³ ~ 2²³-1（符号要占一位）。
2. 浮点数类型：FLOAT（4）、DOUBLE（8）。

   - 计算机的浮点数表示有时是不精确的。

   - FLOAT(M, D) 、DOUBLE(M, D)，可以这样设置浮点数。M 表示该小数最多需要的十进制有效数字个数（2.3有效个数为2），`D`表示该小数的小数点后的十进制数字个数。
 3. 定点数类型：DECIMAL(M, D)，可以保证小数是精确的。了解了 DECIMAL 的存储方式，从小数点位置出发，每个整数每隔9个十进制位划分为1组。
 4. 日期和时间类型：YEAR（1）、DATE（3）、TIME（3）、DATETIME（8）、TIMESTAMP（4）。
 5. 字符串类型：CHAR、VARCHAR、TINYTEXT、TEXT、MEDIUMTEXT、MEDIUMTEXT。

    - VARCHAR 解决了 CHAR 存储长短不一的字符串时浪费空间的问题。
6. ENUM 类型和SET 类型。
7. 二进制类型。

## MySQL打卡第三天

1. 数据库的基本操作：

   - SHOW DATABASES; 查看有哪些数据库

   - CREATE DATABASE 数据库名; 创建数据库

   - USE 数据库名称; 切换数据库

   - DROP DATABASE 数据库名; 删除数据库

   >删除/创建数据库/表，如果数据库/表不存在/已存在，就是报 error级别的错误，后面的代码无法继续执行了。可以加上 IF EXISTS / IF NOT EXISTS，吧 error降到 warning，不会影响后续代码的执行

2. 表的操作

   - SHOW TABLES; 查看有哪些表

   - 创建表

     ```sql
     CREATE TABLE 表名 (
         列名1    数据类型    [列的属性],
         列名2    数据类型    [列的属性],
         列名n    数据类型    [列的属性]
     ) COMMENT '表的注释信息';
     ```

   - DROP TABLE 表1, 表2, ..., 表n; 删除表

   - 查看表结构：

     ```sql
     DESCRIBE 表名;
     DESC 表名;
     EXPLAIN 表名;
     SHOW COLUMNS FROM 表名;
     SHOW FIELDS FROM 表名;
     ```
     
   - 修改表

     ```SQL
     # 修改表名
     ALTER TABLE 旧表名 RENAME TO 新表名; 
     RENAME TABLE 旧表名1 TO 新表名1, 旧表名2 TO 新表名2, ... 旧表名n TO 新表名n;
     
     # 增加列
     ALTER TABLE 表名 ADD COLUMN 列名 数据类型 [列的属性];
     ALTER TABLE 表名 ADD COLUMN 列名 列的类型 [列的属性] FIRST;
     ALTER TABLE 表名 ADD COLUMN 列名 列的类型 [列的属性] AFTER 指定列名;
     
     # 删除列
     ALTER TABLE 表名 DROP COLUMN 列名;
     
     # 修改列信息
     ALTER TABLE 表名 MODIFY 列名 新数据类型 [新属性];
     ALTER TABLE 表名 CHANGE 旧列名 新列名 新数据类型 [新属性];
     
     # 修改列排列位置
     ALTER TABLE 表名 MODIFY 列名 列的类型 列的属性 FIRST;
     ALTER TABLE 表名 MODIFY 列名 列的类型 列的属性 AFTER 指定列名;
     
     # 一条语句中包含多个修改操作
     ALTER TABLE 表名 操作1, 操作2, ..., 操作n;
     ```
     
     

3. 列的属性

   - 默认值
   - NOT NULL属性
   - 主键
   - UNIQUE 属性：不允许重复
   - 外键
   - AUTO_INCREMENT属性：自动增长
   - COMMENT '列的注释
   - ZEROFILL 属性：自动补零

   
   
## MySQL打卡第四天

   今天学了 MySQL 的查找一些基本操作，由于工作中经常用到，所以这里只记一下不太熟悉的知识点:

   1. SELECT DISTINCT 列名 FROM 表名; 可以去重
   
   2. LIKE 模糊查询的时候，`%`代表任意一个字符串，`_`代表任意一个字符。
   
   3. SELECT COUNT(DISTINCT major) FROM student_info;查看`student_info`表中存储了多少个专业的学生信息。
   
   4. 简单查询语句中各子句的顺序：

     ```sql
    	SELECT [DISTINCT] 查询列表
       [FROM 表名]
       [WHERE 布尔表达式]
       [GROUP BY 分组列表 ]
       [HAVING 分组过滤条件]
       [ORDER BY 排序列表]
       [LIMIT 开始行, 限制条数]
     ```

## MySQL打卡第五天

   1. 复习了增删改的一些指令
   
      - get了新指令 `INSERT IGNORE`：忽略报错
      - get了新指令 `INSERT ON DUPLICATE KEY UPDATE` ：解决 `UNIQUE` 约束 或者主键约束
      - 我可以使用 `LIMIT`子句来限制想要删除掉的记录数量，使用`ORDER BY`子句来指定符合条件的记录的删除
   
   2. 弄懂了视图的概念
   
   3. 自定义变量
   
      ```sql
      # 定义并赋值自定义变量
      SET @a = '哈哈';
      SET @b = @a;
      SET @c = (SELECT m1 FROM t1 LIMIT 1);
      
      #另外一种赋值方式
      SELECT n1 FROM t1 LIMIT 1 INTO @a;
      SELECT m1, n1 FROM t1 LIMIT 1 INTO @a, @b;
      ```
   
   4. delimiter 修改语句结束分隔符，用完要改回`;`
   
   5. 认识了存储函数和存储过程。
   
      

## MYSQL打卡第六天

1. 学习了游标的使用，扫除了知识盲点。
2. 学习了触发器和事件的使用
3. 复习了前几天学习的内容。