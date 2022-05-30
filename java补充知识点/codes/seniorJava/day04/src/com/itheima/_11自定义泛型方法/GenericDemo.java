package com.itheima._11自定义泛型方法;

/**
    目标：自定义泛型方法。

    什么是泛型方法？
        定义了泛型的方法就是泛型方法。
    泛型方法的定义格式:
        修饰符 <泛型变量> 返回值类型 方法名称(形参列表){

        }
        注意：方法定义了是什么泛型变量，后面就只能用什么泛型变量。
        泛型类的核心思想：是把出现泛型变量的地方全部替换成传输的真实数据类型。

    需求：给你任何一个类型的数组，都能返回它的内容。

    小结：
        泛型方法和泛型类可以做通用技术架构。
 */
public class GenericDemo {
    public static void main(String[] args) {
        Integer[] nums = {10 , 20 , 30 , 40 , 50};
        String rs1  = arrToString(nums);
        System.out.println(rs1);

        String[] names = {"贾乃亮","王宝绿","陈羽凡"};
        String rs2  = arrToString(names);
        System.out.println(rs2);
    }

    public static <T> String arrToString(T[] nums){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if(nums!=null && nums.length > 0){
            for(int i = 0 ; i < nums.length ; i++ ){
                T ele = nums[i];
                sb.append(i == nums.length-1 ? ele : ele+", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
