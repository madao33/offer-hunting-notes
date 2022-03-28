# [剑指OfferII104.排列的数目](https://leetcode-cn.com/problems/D0F0SV/)

难度中等18收藏分享切换为英文接收动态反馈

给定一个由 **不同** 正整数组成的数组 `nums` ，和一个目标整数 `target` 。请从 `nums` 中找出并返回总和为 `target` 的元素组合的个数。数组中的数字可以在一次排列中出现任意次，但是顺序不同的序列被视作不同的组合。

题目数据保证答案符合 32 位整数范围。

 

**示例 1：**

```
输入：nums = [1,2,3], target = 4
输出：7
解释：
所有可能的组合为：
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
请注意，顺序不同的序列被视作不同的组合。
```

**示例 2：**

```
输入：nums = [9], target = 3
输出：0
```

 

**提示：**

- `1 <= nums.length <= 200`
- `1 <= nums[i] <= 1000`
- `nums` 中的所有元素 **互不相同**
- `1 <= target <= 1000`

 

**进阶：**如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？



## 题解

可以定义`dp[i]`表示选取的元素之和等于`i`的方案数，可以得到`dp[0]=1`，不选取任何元素，元素之和为0，只有一种方案

其他情况，可以断定排列的最后一个元素一定是数组`nums`中的一个元素，假设最后一个元素是`num`，那么一定有`i>=num`，对于元素和为`i-num`的每一种排列，在最后添加一个`num`之后就可以得到`i`的排列，所以
$$
dp[i]=\sum_{j=1, \cdots, nums.length}(dp[i-nums[j]])
$$
得到以下的代码：

```java
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int num : nums) {
                if (i >= num)
                    dp[i] += dp[i-num];
            }
        }
        return dp[target];
    }
}
```

