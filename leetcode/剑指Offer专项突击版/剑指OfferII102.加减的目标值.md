#### [剑指OfferII102.加减的目标值](https://leetcode-cn.com/problems/YaVDxD/)

难度中等14收藏分享切换为英文接收动态反馈

给定一个正整数数组 `nums` 和一个整数 `target` 。

向数组中的每个整数前添加 `'+'` 或 `'-'` ，然后串联起所有整数，可以构造一个 **表达式** ：

- 例如，`nums = [2, 1]` ，可以在 `2` 之前添加 `'+'` ，在 `1` 之前添加 `'-'` ，然后串联起来得到表达式 `"+2-1"` 。

返回可以通过上述方法构造的、运算结果等于 `target` 的不同 **表达式** 的数目。

 

**示例 1：**

```
输入：nums = [1,1,1,1,1], target = 3
输出：5
解释：一共有 5 种方法让最终目标和为 3 。
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
```

**示例 2：**

```
输入：nums = [1], target = 1
输出：1
```

 

**提示：**

- `1 <= nums.length <= 20`
- `0 <= nums[i] <= 1000`
- `0 <= sum(nums[i]) <= 1000`
- `-1000 <= target <= 1000`

## 题解

对于`nums`所有元素的和为`sum`，如果将添加负号的元素的和记作`neg`，添加正号的就是`sum-neg`，那么表达式的和就是`sum-neg-neg`，要求表达式的结果等于`target`，所以可以得到
$$
sum-2neg = target \\
neg = (sum - target)/2
$$
首先判断`sum-target`的值是否是非负偶数，如果不是，直接返回0，如果是，就可以转换为背包问题求解。

所以原问题等价为在`nums`数组中找到元素和为`neg`的背包问题

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0)
            return 0;
        int neg = diff / 2;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = neg; j >= num; j--)
                dp[j] += dp[j-num];
        }
        return dp[neg];
    }
}
```

