# [剑指 Offer II 009. 乘积小于 K 的子数组](https://leetcode-cn.com/problems/ZVAVXX/)

## 题目

给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。

 示例 1:

```
输入: nums = [10,5,2,6], k = 100
输出: 8
解释: 8 个乘积小于 100 的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
```

示例 2:

```
输入: nums = [1,2,3], k = 0
输出: 0
```


提示: 

* `1 <= nums.length <= 3 * 104`
* `1 <= nums[i] <= 1000`
* `0 <= k <= 106`

## 题解

这一题的思路又和[剑指 Offer II 008. 和大于等于 target 的最短子数组](剑指Offer专项突击版/剑指 Offer II 008. 和大于等于 target 的最短子数组.md)有一点类似，同样的使用双指针计算窗口中的子数组的积，得到子数组积小于`k`，那么以该子数组末尾作为结尾的子数组积小于`k`的子数组就有子数组长度这么多个，例如`10, 5`为找到的积小于`k`的子数组，那么就有`10, 5`和`5`两个子数组的积小于`k`，这样遍历直到窗口到数组末尾结束判断，返回结果

```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int len = nums.length;
        if(len == 0)
            return 0;
        int start = 0, end = 0, accu = 1;
        int ans = 0;
        while(end < len) {
            accu *= nums[end];
            while(start < end && accu >= k) accu /= nums[start++];
            if(accu < k) ans += end - start + 1;
            end++;
        } 
        return ans;
    }
}
```

