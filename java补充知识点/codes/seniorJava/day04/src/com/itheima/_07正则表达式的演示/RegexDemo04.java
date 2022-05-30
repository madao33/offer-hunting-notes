package com.itheima._07正则表达式的演示;
/**
    目标：正则表达式在方法中的应用。
        public String[] split(String regex)：
            -- 按照正则表达式匹配的内容进行分割字符串，反回一个字符串数组。
        public String replaceAll(String regex,String newStr)
            -- 按照正则表达式匹配的内容进行替换
 */
public class RegexDemo04 {
    public static void main(String[] args) {
        // 1.split的基础用法
        String names = "贾乃亮,王宝强,陈羽凡";
        // 以“，”分割成字符串数组
        String[] nameArrs = names.split(",");
        for(int i = 0 ; i < nameArrs.length ; i++ ){
            String name = nameArrs[i];
            System.out.println(name);
        }

        System.out.println("----------------------");
        // 2.split集合正则表达式做分割
        String names1 = "贾乃亮lv434fda324王宝强87632fad2342423陈羽凡";
        // 以匹配正则表达式的内容为分割点分割成字符串数组
        String[] nameArrs1 = names1.split("\\w+");
        for(int i = 0 ; i < nameArrs1.length ; i++ ){
            String name = nameArrs1[i];
            System.out.println(name);
        }
        System.out.println("----------------------");
        // 3. public String replaceAll(String regex,String newStr)
        String names2 = "贾乃亮lv434fda324王宝强87632fad2342423陈羽凡";
        // 使用正则表达式定位出内容，替换成/
        System.out.println(names2.replaceAll("\\w+" , "/"));

        String names3 = "贾乃亮,王宝强,羽凡";
        System.out.println(names3.replaceAll(",","-"));
    }
}
