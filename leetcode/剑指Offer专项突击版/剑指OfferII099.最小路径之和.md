#### [剑指OfferII099.最小路径之和](https://leetcode-cn.com/problems/0i0mDW/)

难度中等14

给定一个包含非负整数的 `*m* x *n*` 网格 `grid` ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

**说明：**一个机器人每次只能向下或者向右移动一步。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/11/05/minpath.jpg)

```
输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。
```

**示例 2：**

```
输入：grid = [[1,2,3],[4,5,6]]
输出：12
```

 

**提示：**

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 200`
- `0 <= grid[i][j] <= 100`

## 题解

对于到达网格`grid[i][j]`的路径最小的数字总和可以定义为`dp[i][j]`，可以得到边界`i=0,j=0`的时候，也就是到达起点的路径数字总和为零`dp[0][0]=0`。因为机器人每次只能向下或者向右移动一步，所以`dp[i][j]`的更替只能通过左边或者上边的网格计算路径，递归的方程如下所示：
$$
dp[i][j] = Math.min(dp[i][j-1]+grid[i][j-1], dp[i-1][j] + grid[i-1][j])
$$

> * 为了计算最小的路径数字，需要将`dp[][]`数组除了`(0,0)`外，其他的值全部初始化为`Integer.MAX_VALUE`，防止计算最小值得到初始化全部为0
> * 在进行递归的时候，一些边界的网格可能不存在左边或者上边的表格，需要判断`i>0`或者`j>0`，才进行相应的路径计算

```java
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                dp[i][j] = Integer.MAX_VALUE;
        }

        dp[0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) dp[i][j] = Math.min(dp[i][j], grid[i-1][j] + dp[i-1][j]);
                if (j > 0) dp[i][j] = Math.min(dp[i][j], grid[i][j-1] + dp[i][j-1]);
            }
        }
        return dp[m-1][n-1] + grid[m-1][n-1];
    }
}
```

* 时间复杂度：$O(N^2)$
* 空间复杂度：$O(N^2)$