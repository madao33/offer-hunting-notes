package com.itheima._10自定义注解;
/**

 目标：我们之前都是用别人写好的注解，今天我们自己来做注解。

 自定义注解的格式：
     修饰符 @interface 注解名{
          // 注解属性
     }

  小结：
        自定义注解用@interface关键字。
        使用注解的格式：@注解名称。
        注解默认可以标记很多地方。
 */
@Book
@MyTest
public class MyBook {
    @Book
    @MyTest
    private MyBook(){

    }
    @Book
    @MyTest
    public static void main(@MyTest String[] args) {
        @MyTest
        @Book
        int age = 12;
    }
}

@interface Book{
}

@interface MyTest{

}