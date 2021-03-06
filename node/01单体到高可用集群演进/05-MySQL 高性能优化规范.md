## MySQL 高性能优化规范

> 参考 https://www.cnblogs.com/huchong/p/10219318.html

### 数据库命令规范

1. 所有数据库对象名称必须使用小写字母并用下划线分割。
2. 所有数据库对象名称禁止使用 `MySQL` 保留关键字（如果表名中包含关键字查询时，需要将其用单引号括起来）。
3. 数据库对象的命名要能做到见名识意，并且最后不要超过 32 个字符。
4. 临时库表必须以 `tmp_` 为前缀并以日期为后缀，备份表必须以  `bak_` 为前缀并以日期 (时间戳) 为后缀。
5. 所有存储相同数据的列名和列类型必须一致（一般作为关联列，如果查询时关联列类型不一致会自动进行数据类型隐式转换，会造成列上的索引失效，导致查询效率降低）。

### 数据库基本设计规范

1. 所有数据库必须使用`Innodb`引擎。
2. 数据库和表的字符集统一使用 `UTF8`。
3. 所有的表和字段都必须添加注释。
4. 尽量控制单表数据量的大小，建议控制在500万以内。
5. 谨慎使用 `MySQL `分区表。
6. 尽量做到冷热数据分离，减少表的宽度。
7. 禁止在表中建立预留字段。
8. 禁止在数据库中存储图片,文件等大的二进制数据。
9. 禁止在线上做数据库压力测试。
10. 禁止从开发环境，测试环境直接连接生产环境数据库。

### 数据库字段设计规范

1. 优先选择符合存储需要的最小数据类型。
2. 避免使用 TEXT,BLOB 数据类型，最常见的 TEXT 类型可以存储 64k 的数据。
3. 避免使用 `ENUM`类型。
4. 尽可能把所有列定义为 NOT NULL。
5. 使用 TIMESTAMP(4 个字节) 或 DATETIME 类型 (8 个字节) 存储时间。
6. 同财务相关的金额类数据必须使用 decimal 类型。

### 索引设计规范

1. 限制每张表上的索引数量,建议单张表索引不超过 5 个。
2. 禁止给表中的每一列都建立单独的索引。
3. 每个 Innodb 表必须有个主键：

   - 不要使用更新频繁的列作为主键，不适用多列主键（相当于联合索引）。
   - 不要使用 UUID,MD5,HASH,字符串列作为主键（无法保证数据的顺序增长）。
   - 主键建议使用自增 ID 值。
4. 常见的索引列建议：

   - 出现在 SELECT、UPDATE、DELETE 语句的 WHERE 从句中的列。
   - 包含在 ORDER BY、GROUP BY、DISTINCT 中的字段。
   - 并不要将符合 1 和 2 中的字段的列都建立一个索引， 通常将 1、2 中的字段建立联合索引效果更好。
   - 多表 join 的关联列。
5. 如何选择索引列的顺序：

   - 区分度最高的放在联合索引的最左侧（区分度=列中不同值的数量/列的总行数）。
   - 尽量把字段长度小的列放在联合索引的最左侧（因为字段长度越小，一页能存储的数据量越大，IO 性能也就越好）。
   - 使用最频繁的列放到联合索引的左侧（这样可以比较少的建立一些索引）。
6. 避免建立冗余索引和重复索引（增加了查询优化器生成执行计划的时间）：

   - 重复索引示例：primary key(id)、index(id)、unique index(id)。
   - 冗余索引示例：index(a,b,c)、index(a,b)、index(a)。
7. 对于频繁的查询优先考虑使用覆盖索引。
8. 尽量避免使用外键约束。

### 数据库 SQL 开发规范

1. 建议使用预编译语句进行数据库操作

2. 避免数据类型的隐式转换

3. 充分利用表上已经存在的索引

4. 数据库设计时，应该要对以后扩展进行考虑

5. 程序连接不同的数据库使用不同的账号，进制跨库查询

6. 禁止使用 SELECT * 必须使用 SELECT <字段列表> 查询

7. 禁止使用不含字段列表的 INSERT 语句

8. 避免使用子查询，可以把子查询优化为 join 操作

9. 避免使用 JOIN 关联太多的表

10. 减少同数据库的交互次数

11. 对应同一列进行 or 判断时，使用 in 代替 or

12. 禁止使用 order by rand() 进行随机排序

13. WHERE 从句中禁止对列进行函数转换和计算

14. 在明显不会有重复值时使用 UNION ALL 而不是 UNION

15. 拆分复杂的大 SQL 为多个小 SQL

### 数据库操作行为规范

1. 超 100 万行的批量写 (UPDATE,DELETE,INSERT) 操作,要分批多次进行操作

2. 对于大表使用 pt-online-schema-change 修改表结构

3. 禁止为程序使用的账号赋予 super 权限

4. 对于程序连接数据库账号,遵循权限最小原则

