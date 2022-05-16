package com.itheima._05抽象类设计模板模式;

/**
    目标：使用抽象类设计一个模板模式。

    设计模式：是前人（技术大牛，或者一些技术协会，或者一些大型知名的IT公司）
             已经研发好或者设计好或者在实战开发中发现的的优秀软件设计思想，开源出去
             后来者可以直接使用就能够得到很好的软件模式。

    设计模式的目的：得到优秀的软件架构，从而提升代码的可重用性，扩展性，维护性，可读性。

    模板模式是一种设计模式思想：
    模板模式的作用：部分实现，部分抽象，可以极大的简化功能代码，提高开发效率

    写一个模板模式的案例：作文模板。
        作文模板：
        标题和第一段 以及最后一段是固定的，
        正文部分交给使用模板的人自己来实现。
    小结：
        抽象类是部分实现，部分抽象的含义，所以可以设计模板模式。
        好处：模板可以确定的模板自己实现，模板不能确定的定义成抽象方法交给使用模板的人重写。
        可以设计出优秀的设计模式，提升开发效率，提高代码的重用性！
 */
public class ExtendsDemo {
    public static void main(String[] args) {
        Student xiaoMa = new Student();
        xiaoMa.write();

        Teacher boZai = new Teacher();
        boZai.write();
    }
}

class Teacher extends Template{
    @Override
    public String writeMain() {
        return "\t\t我爸就是好，有多好，做他儿子才能懂~~~";
    }
}

class Student extends Template{
    @Override
    public String writeMain() {
        return "\t\t我爸爸很牛，我爸爸是马云，就是爽，很有钱~~~~";
    }
}
// 1.写一个模板类：代表了作文模板。
abstract class Template{
    private String title = "\t\t\t\t\t\t《我的爸爸》";
    private String one = "\t\t我的爸爸很牛逼，到底有多牛呢，请看如下说明：";
    private String last = "\t\t以上就是我的爸爸，简直太好了，下辈子还要做他儿子！";

    // 2.提供一个写作文方法
    public void write(){
        System.out.println(title);
        System.out.println(one);
        // 正文：正文部分模板是不知道怎么写的！应该把正文部分定义成抽象方法
        // 交给使用模板的子类重写！
        System.out.println(writeMain());
        System.out.println(last);
    }

    // 正文部分定义成抽象方法，交给子类重写！！
    public abstract String writeMain();
}