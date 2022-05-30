package com.itheima._07正则表达式的演示;
/**
    目标：正则表达式的演示。

    正则表达式的作用：
        是一些特殊字符组成的校验规则，可以校验信息的正确性，校验邮箱是否合法
        电话号码，金额等。

    需求：演示不用正则表达式和用正则表达式校验QQ号码。

    小结：
        正则表达式很适合做校验，代码简单，优雅！
 */
public class RegexDemo {
    public static void main(String[] args) {
        System.out.println(checkQQ("42422332"));
        System.out.println(chechQQRegex("42422d332"));
    }

    // 用正则表达式
    public static boolean chechQQRegex(String qq){
        return qq!=null && qq.matches("\\d{4,}");
    }

    // 演示不用正则表达式
    public static boolean checkQQ(String qq){
        // 1.判断是否为null
        if(qq == null){
            return false;
        }else{
            // 2.判断长度是否
            if(qq.length() >= 4){
                // 3.判断是否全部是数字
                // 先定义一个变量存储最终校验的结果
                boolean rs = true ; // 默认认为合法！
                // 4.遍历字符串中的每个字符
                for(int i = 0 ; i < qq.length() ; i++ ){
                    char ch = qq.charAt(i);
                    if(ch < '0' || ch > '9'){
                        rs = false ; // 不合法！！
                    }
                }
                return rs;
            }else{
                return false;
            }
        }
    }
}
