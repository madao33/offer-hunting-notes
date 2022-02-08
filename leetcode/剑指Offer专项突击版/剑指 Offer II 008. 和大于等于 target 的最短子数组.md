# [剑指 Offer II 008. 和大于等于 target 的最短子数组](https://leetcode-cn.com/problems/2VG8Kg/)

## 题目

给定一个含有 n 个正整数的数组和一个正整数 `target `。

找出该数组中满足其和 `≥ target `的长度最小的 连续子数组 `[numsl, numsl+1, ..., numsr-1, numsr]` ，并返回其长度。如果不存在符合条件的子数组，返回` 0` 。

 示例 1：

```
输入：target = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。
```

示例 2：

```
输入：target = 4, nums = [1,4,4]
输出：1
```

示例 3：

```
输入：target = 11, nums = [1,1,1,1,1,1,1,1]
输出：0
```


提示：

* `1 <= target <= 109`
* `1 <= nums.length <= 105`
* `1 <= nums[i] <= 105`


进阶：

如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。

## 题解

可以考虑滑动窗口的方法，设置两个指针`start`和`end`分别指向滑动窗口的开始和结尾，初始设置两个指针都为0，然后`end`右移直到餐口的和大于`target`，这时候判断窗口长度$end-start+1$是否为最小，然后将`start`右移直到窗口和小于`target`，然后在将`end`右移，重复以上的操作直到`end >= len`

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        if(len == 0)
            return 0;
        int start = 0, end = 0, sum = 0;
        int ans = Integer.MAX_VALUE;
        while(end < len){
            sum += nums[end];
            while(sum >= target){
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
```

* 时间复杂度  $O(n)$
* 空间复杂度  $O(1)$