package com.itheima._17引用类型作为成员变量的类型;

public class Address {
    private String code;
    private String name;
    private double x;
    private double y;

    public Address() {
    }

    public Address(String code, String name, double x, double y) {
        this.code = code;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * 获取
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
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
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * 设置
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * 获取
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * 设置
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

}
