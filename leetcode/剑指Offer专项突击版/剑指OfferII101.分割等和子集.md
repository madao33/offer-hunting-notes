#### [剑指OfferII101.分割等和子集](https://leetcode-cn.com/problems/NUPfPr/)

难度简单30收藏分享切换为英文接收动态反馈

给定一个非空的正整数数组 `nums` ，请判断能否将这些数字分成元素和相等的两部分。

 

**示例 1：**

```
输入：nums = [1,5,11,5]
输出：true
解释：nums 可以分割成 [1, 5, 5] 和 [11] 。
```

**示例 2：**

```
输入：nums = [1,2,3,5]
输出：false
解释：nums 不可以分为和相等的两部分
```

 



**提示：**

- `1 <= nums.length <= 200`
- `1 <= nums[i] <= 100`

## 题解

如果不能完全分为相同的两半，那么`nums`的和就肯定不是偶数，所以可以计算`sum(nums)=target`，判断`target%2==0`是否成立，不成立就返回`false`，如果成立就可以将问题转换为在`nums`数组中找到子序列和刚好可以等于`target`

可以将这道题理解为背包问题，定义`f(i,j)`为将`i`个物品放入大小为`j`的背包，是否可以刚好放满，对于每个遍历的物品`nums[i]`，可以想到有两种情况：

* 放入背包，能否填满，需要查看`f(i-1, j-nums[i])`
* 不放入背包，需要查看`f(i-1, j)`

所以`f(i, j) = f(i-1, j)||f(i-1, j-nums[i])`

对于边界条件

* 可以得知`f(i, 0)=true`，因为背包为空，可以假定不用放任何物品就可以保证可以放满背包
* `f(0, j)=false`，因为物品数量为0，所以处理背包不为空，否则都是`false`

> 而通过状态转移方程可以看到，`f(i, j)`的更新基本上只和上一行有关，可以将二维数组优化为一维数组，但是注意`j`需要从`target`开始递减，避免覆盖其他值

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int target = 0;
        for (int num : nums)
            target += num;
        if (target % 2 != 0) return false;
        else target >>= 1;

        int n = nums.length;
        boolean[] dp = new boolean[target+1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j-nums[i]];
            }
        }

        return dp[target];
    }
}
```

* 时间复杂度：$O(NT)$
* 空间复杂度：$O(T)$

> `N`为`nums`数组长度，`T`为`Target`