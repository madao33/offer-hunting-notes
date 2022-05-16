package com.itheima._13super调用父类构造器;

/**
    目标：super调用父类构造器。

    特点：
        子类的全部构造器默认一定会调用父类的无参数构造器。

    super(...):可以根据参数选择调用父类的某个构造器。

    小结：
        可以在子类构造器中通过super(...)根据参数选择调用父类的构造器，以便调用
        父类构造器初始化继承自父类的数据。
 */
public class TestDemo {
    public static void main(String[] args) {
        // 创建对象
        Monkey monkey = new Monkey("金丝猴🐒" , 10 , '雄');
        monkey.eatBanana();
    }
}

class Monkey extends Animal{

    public Monkey(String name, int age, char sex) {
        super(name , age , sex) ; // 根据参数匹配调用父类构造器
    }

    public void eatBanana(){
        System.out.println(getName()+"-->"+getAge()+"-->"+getSex()+"在吃🍌~~~");
    }
}

class Animal{
    private String name;
    private int age;
    private char sex;

    public Animal() {
    }

    public Animal(String name, int age, char sex) {
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
}

