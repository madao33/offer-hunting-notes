#### [剑指OfferII103.最少的硬币数目](https://leetcode-cn.com/problems/gaM7Ch/)

难度中等23收藏分享切换为英文接收动态反馈

给定不同面额的硬币 `coins` 和一个总金额 `amount`。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 `-1`。

你可以认为每种硬币的数量是无限的。

 

**示例 1：**

```
输入：coins = [1, 2, 5], amount = 11
输出：3 
解释：11 = 5 + 5 + 1
```

**示例 2：**

```
输入：coins = [2], amount = 3
输出：-1
```

**示例 3：**

```
输入：coins = [1], amount = 0
输出：0
```

**示例 4：**

```
输入：coins = [1], amount = 1
输出：1
```

**示例 5：**

```
输入：coins = [1], amount = 2
输出：2
```

 

**提示：**

- `1 <= coins.length <= 12`
- `1 <= coins[i] <= 231 - 1`
- `0 <= amount <= 104`

##  题解

设置凑成`i`金额最少需要的硬币数为`dp[i]`，那么可以得到转移方程为：
$$
dp[i] = \min_{j=1,\cdots, coins.length}dp[i-coins[j]] + 1
$$
初始化的时候可以认为没有面额为0的硬币，所以`dp[0]=0`，其他的设置为最大的硬币数量+1，也就是全部都是面额为`1`的硬币组合为`amount`，所以硬币数量为`amount+1`，如果最后结果是比`amount`还要大，说明现有的硬币面额无法组成当前的数量，返回`-1`，否则返回硬币最少的数量

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin)
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1); 
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
```

