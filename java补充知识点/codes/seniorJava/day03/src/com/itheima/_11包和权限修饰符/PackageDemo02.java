package com.itheima._11包和权限修饰符;
/**
    目标：权限修饰符。

    权限修饰符：有四种（private -> 缺省 -> protected - > public ）
    可以修饰成员变量，修饰方法，修饰构造器，内部类，不同修饰符修饰的成员能够被访问的权限将受到限制!

    四种修饰符的访问权限范围：
                        private    缺省       protected   public
        本类中           √           √          √           √
        本包下其他类中    X           √          √           √
        其他包下的类中    X           X          X           √
        其他包下的子类中  X           X          √           √

 */
public class PackageDemo02 {

}
