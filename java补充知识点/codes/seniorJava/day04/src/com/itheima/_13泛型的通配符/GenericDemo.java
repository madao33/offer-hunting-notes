package com.itheima._13泛型的通配符;

import java.util.ArrayList;

/**
    目标：泛型通配符。

    需求：开发一个极品飞车的游戏，所有的汽车都能一起参与比赛。

    注意：
        虽然BMW和BENZ都继承了Car
        但是ArrayList<BMW>和ArrayList<BENZ>与ArrayList<Car>没有关系的！泛型没有继承关系！

    通配符：？
        ?可以用在使用泛型的时候代表一切类型。
        E , T , K , V是在定义泛型的时候使用代表一切类型。

    泛型的上下限：
        ? extends Car : 那么?必须是Car或者其子类。(泛型的上限)
        ? super  Car :那么?必须是Car或者其父类。（泛型的下限。不是很常见）
    小结：
        通配符：?可以用在使用泛型的时候代表一切类型。
        ? extends Car : 那么?必须是Car或者其子类。(泛型的上限)
 */
public class GenericDemo {
    public static void main(String[] args) {
        ArrayList<BMW> bmws = new ArrayList<>();
        bmws.add(new BMW());
        bmws.add(new BMW());
        bmws.add(new BMW());
        run(bmws);

        ArrayList<BENZ> benzs = new ArrayList<>();
        benzs.add(new BENZ());
        benzs.add(new BENZ());
        benzs.add(new BENZ());
        run(benzs);

        ArrayList<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog());
        dogs.add(new Dog());
        dogs.add(new Dog());
        // run(dogs); // 就进不来了！
    }

    // 定义一个方法，可以让很多汽车一起进入参加比赛
    public static void run(ArrayList<? extends Car> cars){

    }
}

class Car{
}
class BMW extends Car{

}
class BENZ extends Car{

}
class Dog{

}