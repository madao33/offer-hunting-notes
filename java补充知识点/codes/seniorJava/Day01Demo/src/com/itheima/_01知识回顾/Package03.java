package com.itheima._01知识回顾;

/**
    目标：封装的概念回顾。

    面向对象的三大特征：封装，继承，多态。
    是Java语言的风格。是我们在开发中必须遵循的，即使毫无意义，代码还是要按照这个风格写！！

    封装的作用：
        1.可以提高安全性。
        2.可以实现代码的组件化。

    封装的规范：
        1.建议成员变量都私有：用private修饰。
           private修饰的方法，成员变量，构造器等只能在本类被直接访问。
        2.提供成套的getter+setter方法暴露成员变量的取值和赋值。
           public修饰符，是公开的意义。
    小结：
        封装的核心思想：合理隐藏，合理暴露。
        封装已经成为Java代码的风格，即使代码毫无意义，还是要按照封装的规范写代码。
            成员变量私有，提供getter+setter方法。

 */
public class Package03 {
    private String name;
    private int age ;

    public Package03() {
    }

    public Package03(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

}
