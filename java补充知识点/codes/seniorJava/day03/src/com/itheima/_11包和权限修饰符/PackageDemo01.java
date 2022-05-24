package com.itheima._11包和权限修饰符;

import com.itheima._10内部类_匿名内部类_重点.Dog;

/**
     目标：包和权限修饰符。

     包：
         分门别类的管理各种不同的技术。
         企业的代码必须用包区分。便于管理技术，扩展技术，阅读技术。

     定义包的格式：package 包名; 必须放在类名的最上面。
        一般工具已经帮我们做好了。

     包名的命名规范：
         一般是公司域名的倒写+技术名称：
         http://www.itheima.com => com.itheima.技术名称
         包名建议全部用英文，多个单词用”.“连接，必须是合法标识符，不能用关键字

     注意：
        相同包下的类可以直接访问。
        不同包下的类必须导包,才可以使用！
           导包格式：import 包名.类名;
 */
public class PackageDemo01 {
    public static void main(String[] args) {
        //  相同包下的类可以直接访问。
        Student.inAddr();
        //  不同包下的类必须先导包才可以使用！
        Dog.go();

    }
}
