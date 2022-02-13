# MySQL排序

## 简单排序

查询并排序`order by`，默认是升序

```shell
mysql> select ename, sal from emp order by sal;
+--------+---------+
| ename  | sal     |
+--------+---------+
| SMITH  |  800.00 |
| JAMES  |  950.00 |
| ADAMS  | 1100.00 |
| WARD   | 1250.00 |
| MARTIN | 1250.00 |
| MILLER | 1300.00 |
| TURNER | 1500.00 |
| ALLEN  | 1600.00 |
| CLARK  | 2450.00 |
| BLAKE  | 2850.00 |
| JONES  | 2975.00 |
| SCOTT  | 3000.00 |
| FORD   | 3000.00 |
| KING   | 5000.00 |
+--------+---------+
14 rows in set (0.00 sec)
```

指定降序`desc`，升序是`asc`

```shell
mysql> select ename, sal from emp order by sal desc;
+--------+---------+
| ename  | sal     |
+--------+---------+
| KING   | 5000.00 |
| SCOTT  | 3000.00 |
| FORD   | 3000.00 |
| JONES  | 2975.00 |
| BLAKE  | 2850.00 |
| CLARK  | 2450.00 |
| ALLEN  | 1600.00 |
| TURNER | 1500.00 |
| MILLER | 1300.00 |
| WARD   | 1250.00 |
| MARTIN | 1250.00 |
| ADAMS  | 1100.00 |
| JAMES  |  950.00 |
| SMITH  |  800.00 |
+--------+---------+
14 rows in set (0.00 sec)
```

## 多个字段排序

先按照薪资升序，薪资一样按照名字升序排列

```shell
mysql> select ename, sal from emp order by sal asc, ename asc;
+--------+---------+
| ename  | sal     |
+--------+---------+
| SMITH  |  800.00 |
| JAMES  |  950.00 |
| ADAMS  | 1100.00 |
| MARTIN | 1250.00 |
| WARD   | 1250.00 |
| MILLER | 1300.00 |
| TURNER | 1500.00 |
| ALLEN  | 1600.00 |
| CLARK  | 2450.00 |
| BLAKE  | 2850.00 |
| JONES  | 2975.00 |
| FORD   | 3000.00 |
| SCOTT  | 3000.00 |
| KING   | 5000.00 |
+--------+---------+
14 rows in set (0.00 sec)
```

> 根据字段位置排序`select ename, sal from emp order by 2;`，按照第2列排序，一般不建议这样写，不够健壮，字段位置经常可能变化

## 综合案例

```shell
mysql> select ename, sal from emp where sal <= 3000  and sal >= 1250 order by sal;
+--------+---------+
| ename  | sal     |
+--------+---------+
| WARD   | 1250.00 |
| MARTIN | 1250.00 |
| MILLER | 1300.00 |
| TURNER | 1500.00 |
| ALLEN  | 1600.00 |
| CLARK  | 2450.00 |
| BLAKE  | 2850.00 |
| JONES  | 2975.00 |
| SCOTT  | 3000.00 |
| FORD   | 3000.00 |
+--------+---------+
10 rows in set (0.00 sec)
```

> 以上语句的执行顺序是
>
> * `from`
> * `where`
> * `select`
> * `order by`