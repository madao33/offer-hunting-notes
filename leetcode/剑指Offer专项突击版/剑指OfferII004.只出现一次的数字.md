# [剑指 Offer II 004. 只出现一次的数字 ](https://leetcode-cn.com/problems/WGki4K/)

## 题目

给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。

示例 1：

```
输入：nums = [2,2,3,2]
输出：3
```

示例 2：

```
输入：nums = [0,1,0,1,0,1,100]
输出：100
```


提示：

* 1 <= nums.length <= 3 * 104
* -231 <= nums[i] <= 231 - 1
* nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次

## 题解

利用`HashMap`的映射来实现

```java
class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for(int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }

        int res = 0;
        for(Integer key : hashMap.keySet()) {
            if(hashMap.get(key) == 1) {
                res = key;
                break;
            }
        }
        return res;
    }
}
```

