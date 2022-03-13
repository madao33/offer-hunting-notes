#### [剑指OfferII100.三角形中最小路径之和](https://leetcode-cn.com/problems/IlPe0q/)

难度中等6收藏分享切换为英文接收动态反馈

给定一个三角形 `triangle` ，找出自顶向下的最小路径和。

每一步只能移动到下一行中相邻的结点上。**相邻的结点** 在这里指的是 **下标** 与 **上一层结点下标** 相同或者等于 **上一层结点下标 + 1** 的两个结点。也就是说，如果正位于当前行的下标 `i` ，那么下一步可以移动到下一行的下标 `i` 或 `i + 1` 。

 

**示例 1：**

```
输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
输出：11
解释：如下面简图所示：
   2
  3 4
 6 5 7
4 1 8 3
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
```

**示例 2：**

```
输入：triangle = [[-10]]
输出：-10
```

 

**提示：**

- `1 <= triangle.length <= 200`
- `triangle[0].length == 1`
- `triangle[i].length == triangle[i - 1].length + 1`
- `-104 <= triangle[i][j] <= 104`

 

**进阶：**

- 你可以只使用 `O(n)` 的额外空间（`n` 为三角形的总行数）来解决这个问题吗？

## 题解

对于 $(i, j)$ 位置结点，可以定义到该结点的最短路径用`dp[i][j]`表示，那么状态转移只能是`dp[i-1][j-1]`或者是`dp[i-1][j]`这两个结点得到，为了求得最短路径，得到以下状态转移方程
$$
dp[i][i] = min(dp[i-1][j-1], dp[i-1][j])+triangle[i][j]
$$
其中注意两个特殊情况

* `j=0`，那么就只有来自上一行下标相同的结点，也就是`dp[i][j]=dp[i-1][j]+triagngle[i][j]`
*  `j=i`， 也就是说当前结点在每一行的结尾，上一步只可能来自于 $(i-1, j-1)$ ，所以`dp[i][i] = dp[i-1][i-1]+triangle[i][i]`

然后查找`dp`数组最后一行中的最小值，就是自顶向下的最小路径和，也就是$\min_{i=0,\cdots,n-1}(dp[n-1][i])$

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++)
                dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]) + triangle.get(i).get(j);
            dp[i][i] = dp[i-1][i-1] + triangle.get(i).get(i);
        }

        int res = dp[n-1][0];
        for (int i = 1; i < n; i++)
            res = Math.min(res, dp[n-1][i]);

        return res;
    }
}
```

