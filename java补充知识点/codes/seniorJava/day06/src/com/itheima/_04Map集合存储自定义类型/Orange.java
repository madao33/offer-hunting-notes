package com.itheima._04Map集合存储自定义类型;

import java.util.Objects;

public class Orange {
    private String name;
    private double weight;
    private String price;


    public Orange() {
    }

    public Orange(String name, double weight, String price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orange orange = (Orange) o;
        return Double.compare(orange.weight, weight) == 0 &&
                Objects.equals(name, orange.name) &&
                Objects.equals(price, orange.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, price);
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
     * @return weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * 设置
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * 获取
     * @return price
     */
    public String getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    public String toString() {
        return "Orange{name = " + name + ", weight = " + weight + ", price = " + price + "}";
    }
}
