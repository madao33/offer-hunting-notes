package com.itheima._13代码块;

import java.util.ArrayList;

/**
    目标：代码块-实例代码块。

    实例代码块的格式：
         {

         }

    实例代码块的特点：
        -- 无static修饰。
        -- 会与类的对象一起加载，每次创建类的对象的时候，
            实例代码块都会被加载且自动触发执行一次。
        -- 实例代码块的代码在底层实际上是提取到每个构造器中去执行的！

    实例代码块的作用：
        -- 实例代码块可以在创建对象之前进行实例资源的初始化操作。

    小结：
        实例代码块无static修饰,属于对象，与对象一起加载执行。
        实例代码块的代码在底层实际上是提取到每个构造器中去执行的！
        实例代码块可以在创建对象之前进行实例资源的初始化操作。
 */
public class CodeDemo02 {
    private String name;
    private ArrayList<String> lists = new ArrayList<>();
    // 实例代码块！属于对象！与对象一起加载!
    {
        name = "小手";
        lists.add("东");
        lists.add("南");
        lists.add("西");
        lists.add("北");
        System.out.println("实例代码块被触发执行一次~~~~~~~~");
    }

    public CodeDemo02(){

    }
    public CodeDemo02(String name){

    }

    public static void main(String[] args) {
        CodeDemo02 c = new CodeDemo02();
        System.out.println(c.name);
        System.out.println(c.lists);
        new CodeDemo02();
        new CodeDemo02();
    }
}
