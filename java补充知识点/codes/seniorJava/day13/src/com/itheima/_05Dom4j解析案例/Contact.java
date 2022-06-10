package com.itheima._05Dom4j解析案例;

/**
 <contact id="1" vip="true">
     <name>潘金莲</name>
     <gender>女</gender>
     <email>panpan@itcast.cn</email>
 </contact>
 */
public class Contact {
    private int id ;
    private boolean vip;
    private String name ;
    private char sex ;
    private String email ;

    public Contact() {
    }

    public Contact(int id, boolean vip, String name, char sex, String email) {
        this.id = id;
        this.vip = vip;
        this.name = name;
        this.sex = sex;
        this.email = email;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return vip
     */
    public boolean isVip() {
        return vip;
    }

    /**
     * 设置
     * @param vip
     */
    public void setVip(boolean vip) {
        this.vip = vip;
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
     * @return sex
     */
    public char getSex() {
        return sex;
    }

    /**
     * 设置
     * @param sex
     */
    public void setSex(char sex) {
        this.sex = sex;
    }

    /**
     * 获取
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Contact{id = " + id + ", vip = " + vip + ", name = " + name + ", sex = " + sex + ", email = " + email + "}";
    }
}
