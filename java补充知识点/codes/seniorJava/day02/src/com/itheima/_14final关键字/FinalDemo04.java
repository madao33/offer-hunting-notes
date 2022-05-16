package com.itheima._14final关键字;

/**
     目标：final修饰实例成员变量。(了解。用不到)

     final修饰变量的总规则：有且仅能被赋值一次。
     拓展：
        final修饰实例成员变量可以在哪些地方赋值1次：
            1.定义的时候赋值一次。
            2.可以在实例代码块中赋值一次。
            3.可以在每个构造器中赋值一次。
 */
public class FinalDemo04 {
    private final String name = "黑马" ;
    private final String name1;
    private final String name2;

    {
        // 可以在实例代码块中赋值一次。
        name1 = "黑马1";
    }

    public FinalDemo04(){
        name2 = "黑马2";
    }


    public FinalDemo04(String a){
        name2 = "黑马2";
    }

    public static void main(String[] args) {
        FinalDemo04 f1 = new FinalDemo04();
        System.out.println(f1.name);
        //f1.name = "黑马1"; // 第二次赋值 报错！
    }
}
