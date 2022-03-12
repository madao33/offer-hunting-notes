# [剑指OfferII091.粉刷房子](https://leetcode-cn.com/problems/JEj789/)

难度中等24收藏分享切换为英文接收动态反馈

假如有一排房子，共 `n` 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。

当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 `n x 3` 的正整数矩阵 `costs` 来表示的。

例如，`costs[0][0]` 表示第 0 号房子粉刷成红色的成本花费；`costs[1][2]` 表示第 1 号房子粉刷成绿色的花费，以此类推。

请计算出粉刷完所有房子最少的花费成本。

 

**示例 1：**

```
输入: costs = [[17,2,17],[16,16,5],[14,3,19]]
输出: 10
解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
     最少花费: 2 + 5 + 3 = 10。
```

**示例 2：**

```
输入: costs = [[7,6,2]]
输出: 2
```

 

**提示:**

- `costs.length == n`
- `costs[i].length == 3`
- `1 <= n <= 100`
- `1 <= costs[i][j] <= 20`

## 题解

假定第 $i$ 个房间刷成红色，总共的花费是 $red[i]$，那么对于花费可以计算的状态转移方程是：
$$
red[i] = min(blue[i-1], gree[i-1]) + costs[i][0]
$$
其他颜色同样的方式，即第 $i$ 个房间只有三种颜色选择，如果选中红色，那么总共花费最低就是前面第 $i-1$ 个房间选择蓝色或绿色花费最低加上第 $i$ 个房间粉刷为红色的花费

```java
class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        int preRed = costs[0][0], preBlue = costs[0][1], preGreen = costs[0][2];
        for (int i = 1; i < n; i++) {
            int nextRed = Math.min(preBlue, preGreen) + costs[i][0];
            int nextBlue = Math.min(preRed, preGreen) + costs[i][1];
            int nextGreen = Math.min(preRed, preBlue) + costs[i][2];

            preRed = nextRed;
            preBlue = nextBlue;
            preGreen = nextGreen;
        }

        return Math.min(preRed, Math.min(preBlue, preGreen));
    }
}
```

* 时间复杂度：$O(N)$
* 空间复杂度：$O(1)$