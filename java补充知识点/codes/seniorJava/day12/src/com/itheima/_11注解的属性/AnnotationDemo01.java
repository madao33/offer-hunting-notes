package com.itheima._11注解的属性;


/**
     目标：注解的属性：

     属性的格式
            - 格式1：数据类型 属性名();
            - 格式2：数据类型 属性名() default 默认值;

     属性适用的数据类型:
            八种数据数据类型(int，short，long，double，byte
             ，char，boolean，float)
            String，Class
            以上类型的数组形式都支持

     小结：
        注解可以有属性，属性名必须带()
        在用注解的时候，属性必须赋值，除非这个属性有默认值！！
 */
@MyBook(name="《精通Java基础》",authors = {"播仔","Dlei","播妞"} , price = 99.9 )
public class AnnotationDemo01 {
    @MyBook(name="《精通MySQL数据库入门到删库跑路》",authors = {"小白","小黑"} ,
            price = 19.9 , address = "北京")
    public static void main(String[] args) {

    }
}

// 自定义一个注解
@interface MyBook{
    String name();
    String[] authors(); // 数组
    double price();
    String address() default "广州";
}






