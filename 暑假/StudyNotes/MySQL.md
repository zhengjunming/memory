# SQL语句
1. 查询语句：select关键字。
2. DML（数据操作语言）：insert、update、delete。
3. DDL（数据定义语言）：create、alter、drop、truncate。
4. DCL（数据控制语言）：grant、revoke。
5. 事务控制语句：commit、rollback、savepoint。
6. SQL的关键字不分大小写。
* 检索单个列
``` mysql
select name from table;
```
* 检索多个列
```mysql
select name1,name2,... from table;
```
* 检索所有列
```mysql
select * from table;
```
* 限制结果
* 返回不多于number行
```mysql
select name
from table
limit number;
```
* 返回从行num1开始的num2行
```mysql
select name
from table
limit num1, num2;
```
* 升序排序
```mysql
select name
from table
order by name;
```
* 多个列排序
```mysql
select name1,name2,...
from table 
order by name1,name2;
```
* 降序排序
```mysql
select name
from table
order by name DESC;
```
* 如果想在多个列上进行降序排序，必须对每个列指定DESC关键字。
#### 过滤条件
* 使用where子句搜索（只返回name1 = xxx的列）
```mysql
select name1,name2
from table
where name1 = xxx;
```
* 组合where子句
* 操作符
  *  AND操作符
  *  OR操作符（计算次序比AND低）
  *  IN操作符
  *  NOT操作符（否定其他条件）
* 检索name3从num1到num2的列，并显示name1和name2列。
``` mysql
select name1, name2
from table
where name3 IN(num1, num2);
```
#### 使用通配符进行过滤
* like操作符
* 百分号(%)通配符：表示任何字符出现任意次数。(**不能匹配NULL**)例如查找所有以abc开头的name：
```mysql
select name
from table
where name like 'abc%';
```
* 检索任意地方出现词abc的name
```mysql
select name
from table
where name like '%abc%';
```
* 检索以a开头，以b结尾的name
```mysql
select name
from table
where name like 'a%b';
```
* 下划线（_）通配符：与%类似，但只能匹配一个字符。
* **注意**：**通配符搜索时间较长，应该尽量避免使用。**
#### 使用正则表达式
* REGEXP操作符
* .：可以匹配任意一个字符
``` mysql
select name
from table
where name REGEXP '.000'
order by name;
```
* 区分大小写：BINARY关键字
```mysql
where name REGEXP BINARY 'Abc .000'
```
* OR匹配：|。例如'1000|2000'。
* 匹配几个字符之一:[]。例如'[123]abc'匹配1abc或2abc或3abc。
* 匹配特殊字符：例如匹配.  。应使用\\\\.。则应使用\\\\为前导。
* 定位符
  * ^：文本的开始
  * $：文本的结尾
  * [[:<:]]：词的开始
  * [[:>:]]：词的结尾
#### 创建计算字段
* 拼接字段：使用Concat()函数拼接两个列
```mysql
select Concat(name1, '(', name2, ')')
from table;
```
* 删除数据右侧多余的空格：RTrim()函数。
```mysql
select Concat(RTrim(name), '(',RTrim( name2),')')
from table;
```
* 删除左侧空格（LTrim函数），去掉串左右两边空格（Trim函数）。
* 使用别名：AS关键字
```mysql
select Concat(RTrim(name1), '(', RTrim(name), ')') AS title
from table;
```
* 结果显示的是以title命名的拼接列。
#### 分组数据
* 创建分组
```mysql
select name, COUNT(*) AS num
from table
GROUP BY name;
```
* 过滤分组：HAVING关键字（除分组概念，其他与WHERE一致）。
```mysql
select name, COUNT(*) AS num
from table
GROUP BY name
HAVING COUNT(*) >= 2;
```
#### 联结表
* 可联结多个表
* 外键：外键为某个表中的一列，它包含另一个表的主键值，定义了两个表之间的关系。
* 创建联结
```mysql
select name1, name2
from table1, table2
where table1.id = table2.id;
```
* 内部联结（与上面效果一样）
```mysql
select name1, name2
from table1 INNER JOIN table2
ON table1.id = table2.id;
```
#### 组合查询
* 创建组合查询： UNION关键字
```mysql
select name1, name2
from table
where name1(条件)
UNION 
select name1, name2
from table
where name2(条件);
```
* 一个表尽量不要用UNION，使用where的操作符即可。
* 对组合查询结果排序：
* 在用UNION组合查询时，只能使用一条OEDER BY子句，必须出现在最后一条select语句之后。
#### 全文本搜索
* 全文本搜索
```mysql
select name
from table
where Macth(name) Against('...');
```
* 查询扩展
```mysql
select name
from table
where Macth(name) Against('...' WITH QUERY EXPANSION);
```
* 布尔文本搜索（可使用布尔操作符）
```mysql
select name
from table
where Macth(name) Against('...' IN BOOLEAN MODE);
```
#### 插入数据
* 插入一行
```mysql
insert into tablename values(data1,data2,...);
```

* 插入多行
```mysql
insert into tablename
values(data1, data2,...),
(data3, data4,...);
```
#### 更新数据
* 更新某个数据
```mysql
update tablename
set name1 = data1
where name2 = data2;
```
* 更新多个数据
```mysql
updeta tablename
set name1 = data1
    name2 = data2
where name3 = data3;
```
#### 删除数据
``` mysql
delete from tablename
where name = data;
```
#### 创建表
* 建表语句
```mysql
create table [模式名.]表名
(
    # 可以有多个列定义
    columnNamel datatype [default expr],
    ...
);
```
#### NOT NULL约束
> 在创建时后面加NOT NULL，防止数据是空值

```mysql
create table tablename
(
    id   int    NOT NULL
);
```
#### 更新表
```mysql
alter table 表名
add/modify
(
    # 可以有多个列定义
    columnNamel datatype [default expr],
    ...
);
```
* 删除列
```mysql
alter table 表名
drop cloumn_name
```
* 重命名数据表
```mysql
alter table 表名
rename newName;
# 或者
rename table oldname to newname;
```
* 改变列名
```mysql
alter table 表名
change old_name new_name;
```
* 显示表列
```mysql
show columns from table;
```
#### UNIQUE约束(唯一约束)
##### 1、列级约束
```mysql
create table tablename
(
    # 在后面加unique即可
    name type unique
);
```
##### 2、表级约束
* 格式：[constraint 约束名] 约束定义
```mysql
create table test
(
    test_name type,
    test_pass type,
    unique (test_name),
    constraint test_uk unique(test_pass)
);
```
* 上面建表语句为test_name、test_pass分别建立了唯一约束，所以这两列都不能出现重复值。
* 两列组合建立唯一约束
```mysql
create table test
(
    test_name type,
    test_pass type,
    constraint test_uk unique(test_name, test_pass)
);
```
* 修改约束
```mysql
alter table tablename
add unique(name1, name2);
# 或者
modify name type unique;
```
* 删除约束
```mysql
alter table tablename
drop index 约束名；
```
#### PRIMARY KEY约束（主键约束）
> 主键列的值可用于唯一地标识表中地一条记录，一个表最多只能有一个主键约束。
* 建立约束、修改约束与UNIQUE约束一致
* 删除约束
```mysql
alter table tablename
drop primary key;
```
#### FOREIGN KEY约束（外键约束）
> 通常用于定义两个实体之间的一对多、一对一的关联关系。
* 列级约束
```mysql
create table teacher_table (
    teacher_id int auto_increment primary key,
    teacher_name varchar(20)
);
create table student_table (
    student_id int auto_increment primary key,
    student_name varchar(20),
    java_teacher int references teacher_table(teacher_id)
);
```
* 表级约束
```mysql
create table teacher_table (
    teacher_id int auto_increment primary key,
    teacher_name varchar(20)
);
create table student_table (
    student_id int auto_increment primary key,
    student_name varchar(20),
    java_teacher int，
    foreign key(java_teacher) references teacher_table(teacher_id)
);
```

* 多列组合的外键约束（必须使用表级约束）
```mysql
create table teacher_table (
    teacher_name varchar(20),
    teacher_pass varchar(20),
    primary key(teacher_name, teacher_pass)
);
create table student_table (
    student_id int auto_increment primary key,
    student_name varchar(20),
    java_teacher_name varchar(20),
    java_teacher_pass varchar(20),
    foreign key(java_teacher_name, java_teacher_pass) references teacher_table(teacher_name, teacher_pass)
);
```
* 删除外键约束
```mysql
alter table tablename
drop foriegn key 约束名;
```
* 自关联
```mysql
create table tablename (
    id int auto_increment primary key,
    name varchar(20),
    refer_id int,
    foriegn key(refer_id) references tablename(id)
);
```
* 如果想定义当删除主表记录时，从表记录也会随之删除，则需要在建立外键约束后添加**on delete cascade**或**on delete set null**。第一种是删除主表时，从表记录全部级联删除，第二种是删除主表时，把参照该主表记录的从表记录的外键设为null。
#### 索引
* 当表上定义主键约束、唯一约束和外键约束时，系统会为该数据列自动创建对应的索引。
* 一个表可有多个索引。
* 创建索引
```mysql
create index index_name
on tablename (column[, columns]...);
```
* 提高对tablename表基于last_name字段的查询速度。
```mysql
create index index_name
on tablename(last_name);
```
* 同时对多个列建立索引
```mysql
create index index_name
on tablename(first_name, last_name);
```
* 删除tablename表上的index_name
```mysql
drop index index_name
on table;
```