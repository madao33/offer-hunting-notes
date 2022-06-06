package com.itheima._09方法引用方式四_构造器引用;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

/**
   目标：方法引用有四种形式：
        1.静态方法的引用。
        2.实例方法的引用。
        3.特定类型方法的引用。
        4.构造器引用。
   4.构造器引用。
      格式是：类名::new。
      注意点：前后参数一致的情况下，又在创建对象就可以使用构造器引用
      s -> new Student(s) => Student::new

   小结：
        方法引用是可遇不可求，能用则用，不能用就不要用！
 */
public class ConstructorDemo01 {
    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();
        lists.add("java1");
        lists.add("java2");
        lists.add("java3");

        // 集合默认只能转成Object类型的数组。
        Object[] objs = lists.toArray();
        System.out.println("Object类型的数组："+ Arrays.toString(objs));

        // 我们想指定转换成字符串类型的数组！！
        // 最新的写法可以结合构造器引用实现 。
        // default <T> T[] toArray(IntFunction<T[]> generator)
        String[] strs = lists.toArray(new IntFunction<String[]>() {
            @Override
            public String[] apply(int value) {
                return new String[value];
            }
        });

        String[] strs1 = lists.toArray(s -> new String[s] );

        String[] strs2 = lists.toArray(String[]::new);

        System.out.println("String类型的数组："+ Arrays.toString(strs2));


    }
}
