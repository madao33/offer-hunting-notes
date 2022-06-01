package com.itheima._04Set系列集合;

import java.util.*;

/**
    目标：TreeSet集合。

    TreeSet: 不重复，无索引，按照大小默认升序排序!!
    TreeSet集合称为排序不重复集合，可以对元素进行默认的升序排序。

    TreeSet集合自自排序的方式：
        1.有值特性的元素直接可以升序排序。（浮点型，整型）
        2.字符串类型的元素会按照首字符的编号排序。
        3.对于自定义的引用数据类型，TreeSet默认无法排序，执行的时候直接报错，因为人家不知道排序规则。

    自定义的引用数据类型的排序实现：
        对于自定义的引用数据类型，TreeSet默认无法排序
        所以我们需要定制排序的大小规则，程序员定义大小规则的方案有2种：
        a.直接为对象的类实现比较器规则接口Comparable，重写比较方法（拓展方式）
             // 如果程序员认为比较者大于被比较者 返回正数！
             // 如果程序员认为比较者小于被比较者 返回负数！
             // 如果程序员认为比较者等于被比较者 返回0！

        b.直接为集合设置比较器Comparator对象,重写比较方法
             // 如果程序员认为比较者大于被比较者 返回正数！
             // 如果程序员认为比较者小于被比较者 返回负数！
             // 如果程序员认为比较者等于被比较者 返回0！
        注意：如果类和集合都带有比较规则，优先使用集合自带的比较规则。

    小结：
        TreeSet集合对自定义引用数据类型排序，默认无法进行。
        但是有两种方式可以让程序员定义大小规则：
             a.直接为对象的类实现比较器规则接口Comparable，重写比较方法（拓展方式）
             b.直接为集合设置比较器Comparator对象,重写比较方法
        注意：如果类和集合都带有比较规则，优先使用集合自带的比较规则。
 */
public class TreeSetDemo05 {
    public static void main(String[] args) {
        // TreeSet : 排序不重复集合。
        Set<Double> scores = new TreeSet<>();
        scores.add(100.0);
        scores.add(99.9);
        scores.add(69.5);
        scores.add(0.1);
        scores.add(89.3);
        System.out.println(scores);

        // 字符串按照首字符的编号进行排序。
        Set<String> names = new TreeSet<>();
        names.add("Jack");
        names.add("rose");
        names.add("Dlei");
        names.add("about");
        names.add("曹雪芹");
        names.add("bozai");
        names.add("caocao");
        names.add("angel");
        System.out.println(names);

        // 引用数据类型定义TreeSet集合。
        Set<Employee> employees = new TreeSet<>();
        employees.add(new Employee("播仔",6500.0,21));
        employees.add(new Employee("播妞",7500.0,19));
        employees.add(new Employee("乔治",4500.0,23));
        System.out.println(employees);


        // public TreeSet(Comparator<? super E> comparator)
        // 集合自带比较器对象
        // 如果类和集合都存在大小规则，默认使用集合自带的规则进行大小排序！！
        Set<Employee> employees1 = new TreeSet<>(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                // o1比较者   o2被比较者
                // 如果程序员认为比较者大于被比较者 返回正数！
                // 如果程序员认为比较者小于被比较者 返回负数！
                // 如果程序员认为比较者等于被比较者 返回0！
                return o1.getAge() - o2.getAge();
            }
        });
        employees1.add(new Employee("播仔",6500.0,21));
        employees1.add(new Employee("播妞",7500.0,19));
        employees1.add(new Employee("乔治",4500.0,23));
        System.out.println(employees1);
    }
}
