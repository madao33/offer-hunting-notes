package com.itheima._14this和super关键字使用总结;

/**
    总结与拓展：this和super关键字使用总结

    this代表了当前对象的引用（继承中指代子类对象）：
        this.子类成员变量。
        this.子类成员方法。
        this(...):可以根据参数匹配访问本类其他构造器。（还没有学习）
    super代表了父类对象的引用（继承中指代了父类对象空间）
        super.父类成员变量。
        super.父类的成员方法。
        super(...):可以根据参数匹配访问父类的构造器。

    拓展：this(...)根据参数匹配访问本类其他构造器。

    注意：
        this(...)借用本类其他构造器。
        super(...)调用父类的构造器。
        this(...)和super(...)必须放在构造器的第一行，否则报错!
        所以this(...)和super(...)不能同时出现在构造器中!!!

    Java是不是最好的语言？
         只能找到合适的语言，而不能找到最好的语言。
         Python
         Go语言
         C语言
         Java WEB
         微软的技术。


 */
public class ThisDemo {
    public static void main(String[] args) {
        // 需求：希望如果不写学校默认就是”黑马“！
        Student zbj = new Student("天蓬元帅", 1000 );
        System.out.println(zbj.getName());
        System.out.println(zbj.getAge());
        System.out.println(zbj.getSchoolName());

        Student swk = new Student("齐天大圣", 2000, "清华大学" );
        System.out.println(swk.getName());
        System.out.println(swk.getAge());
        System.out.println(swk.getSchoolName());
    }
}

class Student{
    private String name ;
    private int age ;
    private String schoolName ;

    public Student() {
    }

    public Student(String name , int age){
        // 借用兄弟构造器的功能！
        this(name , age , "黑马");
    }

    public Student(String name, int age, String schoolName) {
        this.name = name;
        this.age = age;
        this.schoolName = schoolName;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
