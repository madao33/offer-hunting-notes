package com.itheima._17引用类型定义成员变量;

/**
    目标：引用类型定义成员变量

    成员变量：
        修饰符 数据类型 变量名称;
            -- 数据类型可以是：基本数据类型、引用数据类型。
    小结：
        引用类型也可以定义成员变量，该成员变量有时候称为复合类型的变量。
 */
public class TestDemo {
    public static void main(String[] args) {
        Student s = new Student();
        // 赋值：
        s.setName("睡神");
        s.setSex('男');
        s.setAge(23);
        Class c = new Class("Java SE基础进阶就业班","126","播仔老师");
        s.setClazz(c);

        // 取值
        System.out.println(s.getName());
        System.out.println(s.getSex());
        System.out.println(s.getAge());
        // 取学生对象中的班级对象
        Class c1 = s.getClazz();
        System.out.println(c1.getName());
        System.out.println(c1.getCode());
        System.out.println(c1.getTeacherName());

        // 简化写法
        System.out.println(s.getClazz().getTeacherName());

    }
}

class Student{
    private String name;
    private int age ;
    private char sex;
    // 引用类型定义成员变量
    private Class clazz;

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

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}

// 班级也定义成一个类（信息更丰富）
class Class{
    private String name;
    private String code ;
    private String teacherName;

    public Class(){

    }

    public Class(String name, String code, String teacherName) {
        this.name = name;
        this.code = code;
        this.teacherName = teacherName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}