# MySQL查询语句

## 简单查询

* 查询一个字段：`select 字段 from table;`

  ```shel
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

  ```shell
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

  ```
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

  > 但是用`*`有缺点，在实际应用中比较缓慢，会将`*`转换为字段，需要花费一定时间，也不具有较高的可读性

* 给查询的字段