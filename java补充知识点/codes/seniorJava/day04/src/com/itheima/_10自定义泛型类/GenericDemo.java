package com.itheima._10自定义泛型类;

import java.util.ArrayList;

/**
    目标：自定义泛型类。

    引入：
        我们之前用的泛型都是别人写好的，接下来我们来自定义泛型类使用。

    泛型类的概念：
        使用了泛型定义的类就是泛型类。

    泛型类的格式：
        修饰符 class 类名<泛型变量>{

        }
        泛型变量建议使用 E , T , K , V

    需求：模拟ArrayList集合自定义一个集合MyArrayList集合。
    泛型类的核心思想：是把出现泛型变量的地方全部替换成传输的真实数据类型。

    小结：
        自定义泛型的核心思想：是把出现泛型变量的地方全部替换成传输的真实数据类型。
 */
public class GenericDemo {
    public static void main(String[] args) {
        MyArrayList<String> lists = new MyArrayList<String>();

        MyArrayList<String> lists1 = new MyArrayList<>();
        lists1.add("java");
        lists1.add("mysql");
        lists1.remove("java");
        System.out.println(lists1);
    }
}

class MyArrayList<E>{

    private ArrayList lists = new ArrayList();

    public void add(E e){
        lists.add(e);
    }

    public void remove(E e){
        lists.remove(e);
    }
    @Override
    public String toString() {
        return lists.toString();
    }
}