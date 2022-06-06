package com.itheima._13元注解;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**

     目标：元注解

     元注解是sun公司提供的。
     元注解是用在自定义注解上的注解。
     元注解是用来注解自定义注解的。

     元注解有两个：
         @Target:约束自定义注解只能在哪些地方使用，
             -- 但是默认的注解可以在类，方法，构造器，成员变量，... 使用。

         @Retention：申明注解的生命周期
             -- 申明注解的作用范围：编译时，运行时。

     @Target
          * 作用：用来标识注解使用的位置，如果没有使用该注解标识，则自定义的注解可以使用在任意位置。
          * 可使用的值定义在ElementType枚举类中，常用值如下
             TYPE，类，接口
             FIELD, 成员变量
             METHOD, 成员方法
             PARAMETER, 方法参数
             CONSTRUCTOR, 构造器
             LOCAL_VARIABLE, 局部变量

     @Retention
         作用：用来标识注解的生命周期(有效存活范围)
          * 可使用的值定义在RetentionPolicy枚举类中，常用值如下
          * SOURCE：注解只作用在源码阶段，生成的字节码文件中不存在
          * CLASS：注解作用在源码阶段，字节码文件阶段，运行阶段不存在，默认值.
          * RUNTIME：注解作用在源码阶段，字节码文件阶段，运行阶段（开发常用）
     小结：
        @Target约束自定义注解可以标记的范围。
        @Retention用来约束自定义注解的存活范围。
 */
public class AnnotationDemo01{
    // @MyTest
    private String name;

    @MyTest
    public static void main( String[] args) {
    }

    @MyTest
    public void testRun(){

    }
}

//@Target({ElementType.METHOD , ElementType.FIELD}) // 申明只能注解方法和成员变量！
@Target(ElementType.METHOD ) // 申明只能注解方法
@Retention(RetentionPolicy.RUNTIME) // 申明注解从写代码一直到运行还在，永远存活！！
@interface MyTest{
}