package com.itheima._12Object类的详细使用;

import java.util.Objects;

public class Student { //extends Object {
    private String name ;
    private int age ;
    private char sex ;

    public Student() {
    }

    public Student(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    // 重写equals只要两个对象的内容一样，我们就认为他们是相等的。
    // zs1.equals(zs2)
    // 比较者：zs1 == this
    // 被比较者：zs2 == obj
//    @Override
//    public boolean equals(Object obj) {
//        // 1.判断obj是否是学生类型。学生只能和学生比较！！
//        if(obj instanceof Student){
//            // obj是学生类型了
//            // 把obj强制类型转换成学生对象
//            Student zs2 = (Student) obj;
//            // 2.判断内容是否一样，一样返回true
//            return this.name.equals(zs2.name) && this.age == zs2.age
//                      && this.sex == zs2.sex;
//
//        }else{
//            // obj不是学生类型
//            return false ;
//        }
//    }

    // 重写equals只要两个对象的内容一样，我们就认为他们是相等的。
    // zs1.equals(zs2)
    // 比较者：zs1 == this
    // 被比较者：zs2 == o
    @Override
    public boolean equals(Object o) {
        // 1.判断是否自己和自己比较，如果是同一个对象比较直接返回true
        if (this == o) return true;
        // 2.判断被比较者是否为null ,以及是否是学生类型。
        if (o == null || this.getClass() != o.getClass()) return false;
        // 3.o一定是学生类型，强制转换成学生，开始比较内容！
        Student student = (Student) o;
        return age == student.age &&
                sex == student.sex &&
                Objects.equals(name, student.name);
    }

    // 重写Object的toString()以便返回对象的内容数据
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
