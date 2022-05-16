package com.itheima._02抽象类的使用;

/**
    目标：抽象类的使用。

    抽象类是为了被继承。

    总结：
        一个类继承了抽象类，必须重写完抽象类的全部抽象方法，否则这个类必须定义成抽象类。
        因为拥有抽象方法的类必须定义成抽象类。
 */
public class AbstractDemo {
    public static void main(String[] args) {
        Techer boZai = new Techer();
        boZai.work();
        boZai.run();

        Manager baoQing = new Manager();
        baoQing.work();
        baoQing.run();
    }
}


class Manager extends Employee{
    @Override
    public void work() {
        System.out.println("班主任需要管理班级~~~~");
    }

     @Override
     public void run() {

     }
 }

class Techer extends Employee{
    @Override
    public void work() {
        System.out.println("老师需要授课~~~~");
    }

    @Override
    public void run() {

    }
}

// 员工（老师，班主任 ） 每个员工都要工作，但是工作内容不一样。
abstract class Employee{
    public abstract void work();
    public abstract void run();
}