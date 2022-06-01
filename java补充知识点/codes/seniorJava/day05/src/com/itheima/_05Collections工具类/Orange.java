package com.itheima._05Collections工具类;

public class Orange implements Comparable {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Orange{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", price='" + price + '\'' +
                '}'+"\n";
    }

    @Override
    public int compareTo(Object o) {
        Orange o2 = (Orange) o;
        // 集合会自动送入两个句子对象来比较！！
        // 认为o1 > o2返回正整数。
        // 认为o1 = o2返回0。
        // 认为o1 < o2返回负整数。
        if(this.weight > o2.weight) return 1;
        if(this.weight < o2.weight) return -1;
        return 0;
    }
}
