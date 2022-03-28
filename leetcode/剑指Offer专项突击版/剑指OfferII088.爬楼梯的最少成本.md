#### [剑指OfferII088.爬楼梯的最少成本](https://leetcode-cn.com/problems/GzCJIP/)

难度简单29收藏分享切换为英文接收动态反馈

数组的每个下标作为一个阶梯，第 `i` 个阶梯对应着一个非负数的体力花费值 `cost[i]`（下标从 `0` 开始）。

每当爬上一个阶梯都要花费对应的体力值，一旦支付了相应的体力值，就可以选择向上爬一个阶梯或者爬两个阶梯。

请找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。

 

**示例 1：**

```
输入：cost = [10, 15, 20]
输出：15
解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
```

 **示例 2：**

```
输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
输出：6
解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
```

 

**提示：**

- `2 <= cost.length <= 1000`
- `0 <= cost[i] <= 999`

## 题解

递归方程可以定义为
$$
waste[i] = \min(waste[i-1]+cost[i-1], waste[i-2] + cost[i-2])
$$
得到代码

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int pre = 0, cur = 0;
        int ans = 0;
        for (int i = 2; i <= len; i++) {
            int next = Math.min(cost[i-1] + cur, cost[i-2] + pre);
            pre = cur;
            cur =next;
        }
        return cur;
    }
}
```