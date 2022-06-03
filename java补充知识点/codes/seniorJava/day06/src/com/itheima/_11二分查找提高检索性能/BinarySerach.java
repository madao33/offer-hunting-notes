package com.itheima._11二分查找提高检索性能;

/**
    目标：二分查找。

    正常查找：从第一个元素开始遍历，一个一个的往后找，综合查找比较耗时。
    二分查找：二分查找的前提：对数组是有要求的,数组必须已经排好序。
        每次先与中间的元素进行比较，如果大于往右边找，如果小于往左边找，如果等于就返回该元素索引位置！
        如果没有该元素，返回-1。综合性能比较好！！
    小结：
        定义一个方法，记录开始的索引位置和结束的索引位置。
        取出中间索引位置的值，拿元素与中间位置的值进行比较，如果小于中间值，结束位置=中间索引-1.
        取出中间索引位置的值，拿元素与中间位置的值进行比较，如果大于中间值，开始位置=中间索引+1.
        循环正常执行的条件是：开始位置索引<=结束位置索引。 否则说明寻找完毕但是还是没有该元素值返回了-1.

 */
public class BinarySerach {
    public static void main(String[] args) {
        // 1.数组
        int[] arr = {10, 14, 21, 38, 45, 47, 53, 81, 87, 99};
        // 2.需求是从数组中二分查询某个元素值的索引（提高性能）
        System.out.println("81的索引是："+binarySerach(arr,23));
    }

    /**
     *
     * @param arr  被检索的数组
     * @param number 被检索的元素值
     * @return  返回元素在数组中的索引值，不存在该元素返回-1
     */
    public static int binarySerach(int[] arr , int number){
        // 3.记录当前区间搜索的开始索引和结束索引。
        int start = 0 ;
        int end = arr.length - 1;
        // 4.定义一个循环，反复去循环元素。
        while(start <= end){
            // 5.取中间索引位置
            int middleIndex = (start + end) / 2 ;
            // 6.判断当前元素与中间元素的大小
            if(number < arr[middleIndex]){
                // 7.往左边继续寻找，结束索引应该-1
                end = middleIndex - 1;
            }else if(number > arr[middleIndex]){
                start = middleIndex + 1;
            }else if(number == arr[middleIndex]){
                return middleIndex;
            }
        }
        // 如果上述循环执行完毕还没有返回索引，说明根本不存在该元素值，直接返回-1
        return -1;
    }
}
