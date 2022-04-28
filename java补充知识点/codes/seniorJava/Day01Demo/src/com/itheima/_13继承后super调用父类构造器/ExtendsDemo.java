package com.itheima._13继承后super调用父类构造器;

/**
    目标：继承后super调用父类构造器。

    子类构造器的第一行可以写一个：super(...)调用父类的指定构造器

    小结：
        子类构造器可以通过super(...)根据参数去调用父类构造器以便初始化继承自父类的成员变量数据！！
 */
public class ExtendsDemo {
    public static void main(String[] args) {
        Monkey jsh = new Monkey("金丝猴",10,'公');
        jsh.eat();
    }
}

class Monkey extends Animal{
    public Monkey(String name , int age , char sex){
        super(name , age , sex);
    }
    @Override
    public void eat(){
        System.out.println(getName()+"->"+getSex()+"-->"+getAge()+"喜欢吃banana!!");
    }
}
class Animal{
    private String name ;
    private int age ;
    private char sex ;

    public Animal() {
    }

    public Animal(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public void eat(){
        System.out.println("动物吃东西~~");
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
}