# MySQL查询语句

## 简单查询

* 查询一个字段：`select 字段 from table;`

  ```mysql
  select dname from dept;
  +------------+
  | dname      |
  +------------+
  | ACCOUNTING |
  | RESEARCH   |
  | SALES      |
  | OPERATIONS |
  +------------+
  4 rows in set (0.00 sec)
  ```

* 查询两个字段或多个字段，使用逗号隔开：`select 字段1, 字段2 from table;`

  ```mysql
  select deptno, dname from dept;
  +--------+------------+
  | deptno | dname      |
  +--------+------------+
  |     10 | ACCOUNTING |
  |     20 | RESEARCH   |
  |     30 | SALES      |
  |     40 | OPERATIONS |
  +--------+------------+
  4 rows in set (0.00 sec)
  ```

* 查询所用字段，可以写上所有字段或者用`*`代替：`select * from table;`

  ```mysql
  select * from dept;
  +--------+------------+----------+
  | DEPTNO | DNAME      | LOC      |
  +--------+------------+----------+
  |     10 | ACCOUNTING | NEW YORK |
  |     20 | RESEARCH   | DALLAS   |
  |     30 | SALES      | CHICAGO  |
  |     40 | OPERATIONS | BOSTON   |
  +--------+------------+----------+
  4 rows in set (0.00 sec)
  ```

  但是用`*`有缺点，在实际应用中比较缓慢，会将`*`转换为字段，需要花费一定时间，也不具有较高的可读性

* 给查询的列起别名：`select deptno as deptnumber, dname as deptname from dept;`

  ```mysql
  select deptno as deptnumber, dname as deptname from dept;
  +------------+------------+
  | deptnumber | deptname   |
  +------------+------------+
  |         10 | ACCOUNTING |
  |         20 | RESEARCH   |
  |         30 | SALES      |
  |         40 | OPERATIONS |
  +------------+------------+
  4 rows in set (0.00 sec)
  ```

  > 使用`as`关键字起别名，但是只是将显示的查询结果显示为别名，原表名没有变化
  >
  > `select`语句是永远不会进行修改操作

  `as`关键字可以省略：`select deptno depthnumber, dname deptname from dept;`

  ```mysql
  mysql> select deptno depthnumber, dname depthname from dept;
  +-------------+------------+
  | depthnumber | depthname  |
  +-------------+------------+
  |          10 | ACCOUNTING |
  |          20 | RESEARCH   |
  |          30 | SALES      |
  |          40 | OPERATIONS |
  +-------------+------------+
  4 rows in set (0.00 sec)
  ```

  别名如果有空格，需要添加一个单引号`‘’`括起来：`select deptno, dname 'dept name' from dept;`

  ```mysql
  mysql> select deptno, dname 'dept name' from dept;
  +--------+------------+
  | deptno | dept name  |
  +--------+------------+
  |     10 | ACCOUNTING |
  |     20 | RESEARCH   |
  |     30 | SALES      |
  |     40 | OPERATIONS |
  +--------+------------+
  4 rows in set (0.00 sec)
  ```

  >  在所有的数据库当中，字符串统一使用**单引号**括起来，单引号是标准，双引号在`oracle`数据库中用不了

* 列参与数学运算：`select ename, sal*12 as '年薪' from emp;`

  ```mysql
  select ename, sal*12 as '年薪' from emp;
  +--------+----------+
  | ename  | 年薪     |
  +--------+----------+
  | SMITH  |  9600.00 |
  | ALLEN  | 19200.00 |
  | WARD   | 15000.00 |
  | JONES  | 35700.00 |
  | MARTIN | 15000.00 |
  | BLAKE  | 34200.00 |
  | CLARK  | 29400.00 |
  | SCOTT  | 36000.00 |
  | KING   | 60000.00 |
  | TURNER | 18000.00 |
  | ADAMS  | 13200.00 |
  | JAMES  | 11400.00 |
  | FORD   | 36000.00 |
  | MILLER | 15600.00 |
  +--------+----------+
  14 rows in set (0.00 sec)
  ```

## 条件查询

不是将表中所有数据都查出来，而是查询符号条件的

```mysql
select field1, field2, ... fieldN from table_name1, table_name2 ...
[where condition1 [and [or]]] condition2 ...
```

以下是比较操作符表格

| 操作符 |                             描述                             |         实例         |
| :----: | :----------------------------------------------------------: | :------------------: |
|   =    |          等号，检测两个值是否相等，如果相等返回true          | (A = B) 返回false。  |
| <>, != |        不等于，检测两个值是否相等，如果不相等返回true        | (A != B) 返回 true。 |
|   >    | 大于号，检测左边的值是否大于右边的值, 如果左边的值大于右边的值返回true | (A > B) 返回false。  |
|   <    | 小于号，检测左边的值是否小于右边的值, 如果左边的值小于右边的值返回true | (A < B) 返回 true。  |
|   >=   | 大于等于号，检测左边的值是否大于或等于右边的值, 如果左边的值大于或等于右边的值返回true | (A >= B) 返回false。 |
|   <=   | 小于等于号，检测左边的值是否小于或等于右边的值, 如果左边的值小于或等于右边的值返回true | (A <= B) 返回 true。 |

列举几个简单的例子

* 等于`=` 

  ```mysql
  mysql> select empno, ename from emp where sal = 800;
  +-------+-------+
  | empno | ename |
  +-------+-------+
  |  7369 | SMITH |
  +-------+-------+
  1 row in set (0.00 sec)
  ```

* 不等于`<>，!=`

  ```mysql
  mysql> select empno, ename from emp where sal <> 800;
  +-------+--------+
  | empno | ename  |
  +-------+--------+
  |  7499 | ALLEN  |
  |  7521 | WARD   |
  |  7566 | JONES  |
  |  7654 | MARTIN |
  |  7698 | BLAKE  |
  |  7782 | CLARK  |
  |  7788 | SCOTT  |
  |  7839 | KING   |
  |  7844 | TURNER |
  |  7876 | ADAMS  |
  |  7900 | JAMES  |
  |  7902 | FORD   |
  |  7934 | MILLER |
  +-------+--------+
  13 rows in set (0.00 sec)
  
  mysql> select empno, ename from emp where sal != 800;
  +-------+--------+
  | empno | ename  |
  +-------+--------+
  |  7499 | ALLEN  |
  |  7521 | WARD   |
  |  7566 | JONES  |
  |  7654 | MARTIN |
  |  7698 | BLAKE  |
  |  7782 | CLARK  |
  |  7788 | SCOTT  |
  |  7839 | KING   |
  |  7844 | TURNER |
  |  7876 | ADAMS  |
  |  7900 | JAMES  |
  |  7902 | FORD   |
  |  7934 | MILLER |
  +-------+--------+
  13 rows in set (0.00 sec)
  ```

* 符合条件`between ... and ...`

  ```mysql
  mysql> select empno, ename, sal from emp where sal >= 2450 and sal <= 3000;
  +-------+-------+---------+
  | empno | ename | sal     |
  +-------+-------+---------+
  |  7566 | JONES | 2975.00 |
  |  7698 | BLAKE | 2850.00 |
  |  7782 | CLARK | 2450.00 |
  |  7788 | SCOTT | 3000.00 |
  |  7902 | FORD  | 3000.00 |
  +-------+-------+---------+
  5 rows in set (0.00 sec)
  
  mysql> select empno, ename, sal from emp where sal between 2450 and 3000;
  +-------+-------+---------+
  | empno | ename | sal     |
  +-------+-------+---------+
  |  7566 | JONES | 2975.00 |
  |  7698 | BLAKE | 2850.00 |
  |  7782 | CLARK | 2450.00 |
  |  7788 | SCOTT | 3000.00 |
  |  7902 | FORD  | 3000.00 |
  +-------+-------+---------+
  5 rows in set (0.00 sec)
  ```

  > `between num1 and num2`使用时必须是`num1 < num2`，而且范围是闭区间

* `null`的查询，需要使用`where filed is num;`

  ```mysql
  mysql> select empno, ename sal, comm from emp where comm is null;
  +-------+--------+------+
  | empno | sal    | comm |
  +-------+--------+------+
  |  7369 | SMITH  | NULL |
  |  7566 | JONES  | NULL |
  |  7698 | BLAKE  | NULL |
  |  7782 | CLARK  | NULL |
  |  7788 | SCOTT  | NULL |
  |  7839 | KING   | NULL |
  |  7876 | ADAMS  | NULL |
  |  7900 | JAMES  | NULL |
  |  7902 | FORD   | NULL |
  |  7934 | MILLER | NULL |
  +-------+--------+------+
  10 rows in set (0.00 sec)
  ```

  > 数据库中的`null`不能使用等号进行衡量，需要使用`is null`。这是因为数据库中的`null`代表什么也没有，它不是一个值，所以不能使用等号衡量。

  ```mysql
  mysql> select empno, ename, sal, comm from emp where comm is not null;
  +-------+--------+---------+---------+
  | empno | ename  | sal     | comm    |
  +-------+--------+---------+---------+
  |  7499 | ALLEN  | 1600.00 |  300.00 |
  |  7521 | WARD   | 1250.00 |  500.00 |
  |  7654 | MARTIN | 1250.00 | 1400.00 |
  |  7844 | TURNER | 1500.00 |    0.00 |
  +-------+--------+---------+---------+
  4 rows in set (0.00 sec)
  ```

* 合并条件

  ```mysql
  mysql> select empno, ename, sal, job from emp where job = 'MANAGER' or job = 'SALESMAN';
  +-------+--------+---------+----------+
  | empno | ename  | sal     | job      |
  +-------+--------+---------+----------+
  |  7499 | ALLEN  | 1600.00 | SALESMAN |
  |  7521 | WARD   | 1250.00 | SALESMAN |
  |  7566 | JONES  | 2975.00 | MANAGER  |
  |  7654 | MARTIN | 1250.00 | SALESMAN |
  |  7698 | BLAKE  | 2850.00 | MANAGER  |
  |  7782 | CLARK  | 2450.00 | MANAGER  |
  |  7844 | TURNER | 1500.00 | SALESMAN |
  +-------+--------+---------+----------+
  7 rows in set (0.00 sec)
  ```

* `and`和`or`优先级的问题

  查询工资大于2500，并且部门编号为10或20的员工

  ```mysql
  mysql> select empno, ename, sal, deptno from emp where sal > 2500 and deptno = 10 or deptno = 20;
  +-------+-------+---------+--------+
  | empno | ename | sal     | deptno |
  +-------+-------+---------+--------+
  |  7369 | SMITH |  800.00 |     20 |
  |  7566 | JONES | 2975.00 |     20 |
  |  7788 | SCOTT | 3000.00 |     20 |
  |  7839 | KING  | 5000.00 |     10 |
  |  7876 | ADAMS | 1100.00 |     20 |
  |  7902 | FORD  | 3000.00 |     20 |
  +-------+-------+---------+--------+
  6 rows in set (0.00 sec)
  ```

  可以看到有出现`sal`为800的结果也出现了，这是因为`and`的优先级较高，上述语句变成了优先判断部门编号为`20`或者工资大于`2500`以及部门编号为`10`的判断语句了，需要添加一个小括号

  ```mysql
  mysql> select empno, ename, sal, deptno from emp where sal > 2500 and (deptno = 10 or deptno = 20);
  +-------+-------+---------+--------+
  | empno | ename | sal     | deptno |
  +-------+-------+---------+--------+
  |  7566 | JONES | 2975.00 |     20 |
  |  7788 | SCOTT | 3000.00 |     20 |
  |  7839 | KING  | 5000.00 |     10 |
  |  7902 | FORD  | 3000.00 |     20 |
  +-------+-------+---------+--------+
  4 rows in set (0.00 sec)
  ```

* `in`多个包含，相当于多个`or`

  ```mysql
  mysql> select empno, ename, job from emp where job in ('manager', 'salesman');
  +-------+--------+----------+
  | empno | ename  | job      |
  +-------+--------+----------+
  |  7499 | ALLEN  | SALESMAN |
  |  7521 | WARD   | SALESMAN |
  |  7566 | JONES  | MANAGER  |
  |  7654 | MARTIN | SALESMAN |
  |  7698 | BLAKE  | MANAGER  |
  |  7782 | CLARK  | MANAGER  |
  |  7844 | TURNER | SALESMAN |
  +-------+--------+----------+
  7 rows in set (0.00 sec)
  
  mysql> select empno, ename, job from emp where job = 'manager' or job = 'salesman';
  +-------+--------+----------+
  | empno | ename  | job      |
  +-------+--------+----------+
  |  7499 | ALLEN  | SALESMAN |
  |  7521 | WARD   | SALESMAN |
  |  7566 | JONES  | MANAGER  |
  |  7654 | MARTIN | SALESMAN |
  |  7698 | BLAKE  | MANAGER  |
  |  7782 | CLARK  | MANAGER  |
  |  7844 | TURNER | SALESMAN |
  +-------+--------+----------+
  7 rows in set (0.00 sec)
  ```

  > `in`后面跟的不是一个范围，而是具体的几个值

  `not in`就表示不是这几个值当中的数据

  ```mysql
  mysql> select empno, ename, job from emp where job not in ('manager', 'salesman');
  +-------+--------+-----------+
  | empno | ename  | job       |
  +-------+--------+-----------+
  |  7369 | SMITH  | CLERK     |
  |  7788 | SCOTT  | ANALYST   |
  |  7839 | KING   | PRESIDENT |
  |  7876 | ADAMS  | CLERK     |
  |  7900 | JAMES  | CLERK     |
  |  7902 | FORD   | ANALYST   |
  |  7934 | MILLER | CLERK     |
  +-------+--------+-----------+
  7 rows in set (0.00 sec)
  ```

* `like`模糊查询，支持`%`和`_`匹配

  `%`匹配任意多个字符

  `_`匹配任意一个字符

  ```mysql
  mysql> select ename from emp where ename like'%O%';
  +-------+
  | ename |
  +-------+
  | JONES |
  | SCOTT |
  | FORD  |
  +-------+
  3 rows in set (0.00 sec)
  
  mysql> select ename from emp where ename like'%T';
  +-------+
  | ename |
  +-------+
  | SCOTT |
  +-------+
  1 row in set (0.00 sec)
  
  mysql> select ename from emp where ename like'k%';
  +-------+
  | ename |
  +-------+
  | KING  |
  +-------+
  1 row in set (0.00 sec)
  
  mysql> select ename from emp where ename like'_A%';
  +--------+
  | ename  |
  +--------+
  | WARD   |
  | MARTIN |
  | JAMES  |
  +--------+
  3 rows in set (0.00 sec)
  
  mysql> select ename from emp where ename like'__R%';
  +--------+
  | ename  |
  +--------+
  | WARD   |
  | MARTIN |
  | TURNER |
  | FORD   |
  +--------+
  4 rows in set (0.00 sec)
  ```

  可以使用`\_`查找含有下划线的

  ```mysql
  mysql> select name from t_student where name like '%\_%';
  +-----------+
  | name      |
  +-----------+
  | madao_233 |
  +-----------+
  1 row in set (0.00 sec)
  ```

  