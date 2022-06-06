package com.itheima._09Properties属性集对象;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

/**

    目标：Properties的概述和使用(框架底层使用，了解这个技术即可)。（保存数据到属性文件）

    Properties：属性集对象。
         其实就是一个Map集合。也就是一个键值对集合。但是我们一般不会当集合使用，
         因为有HashMap。
    Properties核心作用：
         Properties代表的是一个属性文件，可以把键值对的数据存入到一个属性文件中去。
         属性文件：后缀是.properties结尾的文件,里面的内容都是 key=value。

    大家在后期学的很多大型框架技术中，属性文件都是很重要的系统配置文件。
        users.properties
                admin=123456
                dlei=dlei

     需求：使用Properties对象生成一个属性文件，里面存入用户名和密码信息。

     Properties的方法：
     -- public Object setProperty(String key, String value) ： 保存一对属性。
     -- public String getProperty(String key) ：使用此属性列表中指定的键搜索属性值
     -- public Set<String> stringPropertyNames() ：所有键的名称的集合
     -- public void store(OutputStream out, String comments):保存数据到属性文件中去
     -- public void store(Writer fw, String comments):保存数据到属性文件中去

    小结：
        属性集对象Properties实际上是一个Map集合，可以实现把键值对数据保存到
        属性文件中去！！
 */
public class PropertiesDemo01 {
    public static void main(String[] args) throws Exception {
        // a.创建一个属性集对象：Properties的对象。
        Properties properties = new Properties();
        properties.setProperty("admin" , "123456");
        properties.setProperty("dlei" , "101333");
        System.out.println(properties);

        // b.把属性集对象的数据存入到属性文件中去（重点）
        OutputStream os = new FileOutputStream("Day10Demo/src/users.properties");
        /**
         * 参数一：被保存数据的输出管道
         * 参数二：保存心得。就是对象保存的数据进行解释说明！
         */
        properties.store(os , "i am very happy!!我快乐的保存了用户数据!");

    }
}
