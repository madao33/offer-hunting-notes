package com.itheima._04Set系列集合;

import java.util.Objects;

public class Apple {
    private String name;
    private double price ;
    private String color ;

    public Apple() {
    }

    public Apple(String name, double price, String color) {
        this.name = name;
        this.price = price;
        this.color = color;
    }

    // 只要两个对象的内容一样，equals比较的结果一定为true
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apple apple = (Apple) o;
        return Double.compare(apple.price, price) == 0 &&
                Objects.equals(name, apple.name) &&
                Objects.equals(color, apple.color);
    }

    // 只要两个对象的内容一样返回的哈希值也要一样！true
    @Override
    public int hashCode() {
        // Apple a2 = new Apple("阿克苏",39.9 ,"青红色");
        // Apple a3 = new Apple("阿克苏",39.9 ,"青红色");
        //  a2.hashCode() == Objects.hash(阿克苏, 39.9, 青红色)
        //  a3.hashCode() == Objects.hash(阿克苏, 39.9, 青红色)
        return Objects.hash(name, price, color);
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
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 获取
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * 设置
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        return "Apple{name = " + name + ", price = " + price + ", color = " + color + "}";
    }
}
