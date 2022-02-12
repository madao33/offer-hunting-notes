# [剑指 Offer II 010. 和为 k 的子数组](https://leetcode-cn.com/problems/QTMn0o/)

## 题目

给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。

 示例 1 :

```
输入:nums = [1,1,1], k = 2
输出: 2
解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
```

示例 2 :

```
输入:nums = [1,2,3], k = 3
输出: 2
```


提示:

* 1 <= nums.length <= 2 * 104
* -1000 <= nums[i] <= 1000
* -107 <= k <= 107

## 题解

利用前缀和将问题转换为求开头连续子数组为`sum-k`的个数，保证数组的连续性

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int i=0;i<nums.length;++i) {
            sum += nums[i];
            count += map.getOrDefault(sum-k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度： $O(n)$

