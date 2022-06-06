package com.itheima._06方法引用方式一_静态方法的引用;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
   目标：方法引用有四种形式：
        1.静态方法的引用。
        2.实例方法的引用。
        3.特定类型方法的引用。
        4.构造器引用。

   1.静态方法的引用。
      引用格式：
         类名::静态方法。
      简化步骤：
         a.定义一个静态方法，把需要简化的代码放到一个静态方法中去。
      静态方法引用的注意事项
         ” 重要：被引用的方法的参数列表要和函数式接口中的抽象方法的参数列表一致。“
   小结：
        静态方法引用的格式： 类名::静态方法。
        重要：被引用的方法的参数列表要和函数式接口中的抽象方法的参数列表一致,才可以引用简化！

 */
public class MethodDemo01 {
    public static void main(String[] args) {
        List<Student> lists = new ArrayList<>();
        Student s1 = new Student("李铭",18,'男');
        Student s2 = new Student("冯龙",23,'男');
        Student s3 = new Student("王乐乐",21,'男');
        Collections.addAll(lists , s1 , s2 , s3);

        System.out.println(lists);

        Collections.sort(lists, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        // Lambda表达式简化参数二：匿名内部类的Comparator写法!
        Collections.sort(lists, (Student o1, Student o2) -> {
            return o1.getAge() - o2.getAge();
        });

        Collections.sort(lists, (Student o1, Student o2) -> o1.getAge() - o2.getAge());

        Collections.sort(lists, ( o1, o2) -> o1.getAge() - o2.getAge());

        // 使用静态方法进行简化！
        Collections.sort(lists, ( o1, o2) -> Student.compareByAge(o1 , o2));
        // 如果前后参数是一样的，而且方法是静态方法，既可以使用静态方法引用
        Collections.sort(lists, Student::compareByAge);

        System.out.println(lists);
    }
}
