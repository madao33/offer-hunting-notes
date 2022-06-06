package com.itheima._08反射有啥用;

public class Pig {
    private String name ;
    private double weight;
    private String color;
    private String onwer ;
    private String sex ;

    public Pig(String name, double weight, String color, String onwer, String sex) {
        this.name = name;
        this.weight = weight;
        this.color = color;
        this.onwer = onwer;
        this.sex = sex;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOnwer() {
        return onwer;
    }

    public void setOnwer(String onwer) {
        this.onwer = onwer;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
