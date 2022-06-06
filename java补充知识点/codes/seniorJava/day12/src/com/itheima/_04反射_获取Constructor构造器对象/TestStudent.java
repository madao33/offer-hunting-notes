package com.itheima._04反射_获取Constructor构造器对象;

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
    目标：反射_获取Constructor构造器对象.

    反射的第一步是先得到Class类对象。（Class文件）

    反射中Class类型获取构造器提供了很多的API:
         1. Constructor getConstructor(Class... parameterTypes)
            根据参数匹配获取某个构造器，只能拿public修饰的构造器，几乎不用！
         2. Constructor getDeclaredConstructor(Class... parameterTypes)
            根据参数匹配获取某个构造器，只要申明就可以定位，不关心权限修饰符，建议使用！
         3. Constructor[] getConstructors()
            获取所有的构造器，只能拿public修饰的构造器。几乎不用！！太弱了！
         4. Constructor[] getDeclaredConstructors()
            获取所有申明的构造器，只要你写我就能拿到，无所谓权限。建议使用！！
    小结：
        获取类的全部构造器对象： Constructor[] getDeclaredConstructors()
            -- 获取所有申明的构造器，只要你写我就能拿到，无所谓权限。建议使用！！
        获取类的某个构造器对象：Constructor getDeclaredConstructor(Class... parameterTypes)
            -- 根据参数匹配获取某个构造器，只要申明就可以定位，不关心权限修饰符，建议使用！

 */
public class TestStudent {
    // 1. getConstructors:
    // 获取全部的构造器：只能获取public修饰的构造器。
    // Constructor[] getConstructors()
    @Test
    public void getConstructors(){
        // a.反射第一步先得到Class类对象
        Class c = Student.class ;
        // b.getConstructors()：定位全部构造器，只能拿public修饰的！
        Constructor[] cons = c.getConstructors();
        // c.遍历这些构造器
        for (Constructor con : cons) {
            System.out.println(con.getName()+"===>"+con.getParameterCount());
        }
    }

    // 2.getDeclaredConstructors():
    // 获取全部的构造器：只要你敢写，这里就能拿到，无所谓权限是否可及。
    @Test
    public void getDeclaredConstructors(){
        // a.反射第一步先得到Class类对象
        Class c = Student.class ;
        // b.getDeclaredConstructors()：定位全部构造器，只要申明了就可以拿到
        Constructor[] cons = c.getDeclaredConstructors();
        // c.遍历这些构造器
        for (Constructor con : cons) {
            System.out.println(con.getName()+"===>"+con.getParameterCount());
        }
    }

    // 3.getConstructor(Class... parameterTypes)
    // 获取某个构造器：只能拿public修饰的某个构造器
    @Test
    public void getConstructor() throws Exception {
        // a.反射第一步先得到Class类对象
        Class c = Student.class ;
        // b.getConstructor()：定位某个构造器，根据参数匹配，只能拿public修饰的！
        // Constructor con = c.getConstructor(); // 报错!
        Constructor con = c.getConstructor(String.class  , int.class); // 有参数的！!
        // c.构造器名称和参数
        System.out.println(con.getName()+"===>"+con.getParameterCount());
    }

    // 4.getDeclaredConstructor
    // 获取某个构造器：只要你敢写，这里就能拿到，无所谓权限是否可及。
    @Test
    public void getDeclaredConstructor() throws Exception {
        // a.反射第一步先得到Class类对象
        Class c = Student.class ;
        // b.getDeclaredConstructor()：定位某个构造器，根据参数匹配，只要申明了就可以获取
        Constructor con = c.getDeclaredConstructor(); // 可以拿到！定位无参数构造器！
        //Constructor con = c.getDeclaredConstructor(String.class  , int.class); // 有参数的！!
        // c.构造器名称和参数
        System.out.println(con.getName()+"===>"+con.getParameterCount());
    }
}