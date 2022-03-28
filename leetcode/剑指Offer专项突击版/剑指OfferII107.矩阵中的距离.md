# [剑指OfferII107.矩阵中的距离](https://leetcode-cn.com/problems/2bCMpM/)

难度中等13收藏分享切换为英文接收动态反馈

给定一个由 `0` 和 `1` 组成的矩阵 `mat` ，请输出一个大小相同的矩阵，其中每一个格子是 `mat` 中对应位置元素到最近的 `0` 的距离。

两个相邻元素间的距离为 `1` 。

 

**示例 1：**

![img](https://pic.leetcode-cn.com/1626667201-NCWmuP-image.png)

```
输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
输出：[[0,0,0],[0,1,0],[0,0,0]]
```

**示例 2：**

![img](https://pic.leetcode-cn.com/1626667205-xFxIeK-image.png)

```
输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
输出：[[0,0,0],[0,1,0],[1,2,1]]
```

 

**提示：**

- `m == mat.length`
- `n == mat[i].length`
- `1 <= m, n <= 104`
- `1 <= m * n <= 104`
- `mat[i][j] is either 0 or 1.`
- `mat` 中至少有一个 `0 `

## 题解

两次DP动态规划，没有完全掌握

```java
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        // 我们用一个圈包裹住整个dp数组，这样我们在DP的时候就不用判断下标了
        // dp[i + 1][j + 1] 表示mat[i][j] 距离0的最短距离， i,j >= 0
        int[][] dp = new int[rows + 2][cols + 2];
        // 这个圈的外围跟0的距离都是无穷大
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = dp[i][cols + 1] = Integer.MAX_VALUE >> 1; // 最大值取一半即可，避免溢出
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = dp[rows + 1][j] = Integer.MAX_VALUE >> 1;
        }
        // 从左上到右下遍历一次
        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < cols + 1; j++) {
                if (mat[i - 1][j - 1] == 0) {
                    dp[i][j] = 0;
                } else {
                    // 当前位置为1，距离其左上部分0的最短距离为min(左边离0的距离，上边离0的距离) + 1
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        // 从右下到左上遍历一次
        for (int i = rows; i > 0; i--) {
            for (int j = cols; j > 0; j--) {
                // 前面已经算过mat[i][j] = 0 时的情况了
                if (mat[i - 1][j - 1] == 1) {
                    // 当前位置为1，距离其右下部分0的最短距离为min(右边离0的距离，下边离0的距离) + 1
                    dp[i][j] = Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j + 1]) + 1);
                }
            }
        }
        // 取出答案
        int[][] ans = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ans[i][j] = dp[i + 1][j + 1];
            }
        }
        return ans;
    }
}
```

 