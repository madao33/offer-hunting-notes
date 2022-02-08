# [剑指 Offer II 007. 数组中和为 0 的三个数](https://leetcode-cn.com/problems/1fGaJU/)

## 题目

给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a ，b ，c ，使得 a + b + c = 0 ？请找出所有和为 0 且 不重复 的三元组。

示例 1：

```
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
```

示例 2：

```
输入：nums = []
输出：[]
```

示例 3：

```
输入：nums = [0]
输出：[]
```


提示：

* 0 <= nums.length <= 3000
* -105 <= nums[i] <= 105

## 题解

暴力解法的话就是三重循环，但是需要注意去除重复判断，暴力解法的就不尝试了

另一种方法就类似于[剑指 Offer II 006. 排序数组中两个数字之和](剑指Offer专项突击版/剑指 Offer II 006. 排序数组中两个数字之和.md)的思路，首先需要对数组进行排序处理，已知了一个数，另外两个数的和就是已知数的相反数，相当于是已知数为`target`，查找数组中其他数有两个和等于已知数的相反数

* 为了加快运算，外部的循环到大于0时就退出循环
* 双指针判断需要注意是否有相等的数字，有相等的直接跳过

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        Arrays.sort(nums);
        for(int i = 0; i < len; i++) {
            if(nums[i] > 0) break;
            // 去重判断
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = len - 1;
            while(left < right) {
                int total = nums[i] + nums[left] + nums[right];
                if(total == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重判断
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    left++;
                }else if (total < 0) left++;
                else right--;
            }
        }
        return ans;
    }
}
```

