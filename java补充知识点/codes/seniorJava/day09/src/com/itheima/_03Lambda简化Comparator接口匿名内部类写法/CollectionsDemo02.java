package com.itheima._03Lambda简化Comparator接口匿名内部类写法;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
    目标：Lambda简化Comparator接口匿名内部类写法;
 */
public class CollectionsDemo02 {
    public static void main(String[] args) {
        List<Student> lists = new ArrayList<>();
        Student s1 = new Student("李铭",18,'男');
        Student s2 = new Student("冯龙",23,'男');
        Student s3 = new Student("王乐乐",21,'男');
        Collections.addAll(lists , s1 , s2 , s3);

        // 按照年龄进行升序排序！
        Collections.sort(lists, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getAge() - s2.getAge();
            }
        });

        // 简化写法
        Collections.sort(lists ,(Student t1, Student t2) -> {
                return t1.getAge() - t2.getAge();
        });

        Collections.sort(lists ,(Student t1, Student t2) -> t1.getAge() - t2.getAge());

        // 参数类型可以省略
        Collections.sort(lists ,( t1,  t2) -> t1.getAge() - t2.getAge());

        System.out.println(lists);


    }
}
