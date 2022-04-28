package com.itheima._07继承的使用案例;
// 父类（姓名，年龄。通用行为：吃饭）
public class People {
    private String name;
    private int age ;

    // 通用的方法
    public void eat(){
        System.out.println(name+", 在吃饭！");
    }

    public People() {
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
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
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

}
