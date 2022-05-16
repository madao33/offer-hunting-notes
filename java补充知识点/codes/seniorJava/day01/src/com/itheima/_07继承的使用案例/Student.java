package com.itheima._07继承的使用案例;

public class Student extends People {
    // 特有功能
    public void study(){
        System.out.println(getName()+"学生认真学习~~~");
    }
}
