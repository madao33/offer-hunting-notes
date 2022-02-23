# [剑指OfferII040.矩阵中最大的矩形](https://leetcode-cn.com/problems/PLYXKQ/)

难度困难24收藏分享切换为英文接收动态反馈

给定一个由 `0` 和 `1` 组成的矩阵 `matrix` ，找出只包含 `1` 的最大矩形，并返回其面积。

**注意：**此题 `matrix` 输入格式为一维 `01` 字符串数组。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/09/14/maximal.jpg)

```
输入：matrix = ["10100","10111","11111","10010"]
输出：6
解释：最大矩形如上图所示。
```

**示例 2：**

```
输入：matrix = []
输出：0
```

**示例 3：**

```
输入：matrix = ["0"]
输出：0
```

**示例 4：**

```
输入：matrix = ["1"]
输出：1
```

**示例 5：**

```
输入：matrix = ["00"]
输出：0
```

 

**提示：**

- `rows == matrix.length`
- `cols == matrix[0].length`
- `0 <= row, cols <= 200`
- `matrix[i][j]` 为 `'0'` 或 `'1'`

## 题解

对于每一行的矩阵，可以定义一个`heights`数组，对应于这一列数字可以看做是`1`高度的数组，然后通过[剑指OfferII039.直方图最大矩形面积](https://leetcode-cn.com/problems/0ynMMM/)的算法计算出直方图的最大矩形面积

```java
class Solution {
    public int maximalRectangle(String[] matrix) {
        if (matrix.length == 0) return 0;

        int[] heights = new int[matrix[0].length()];
        int maxArea = 0;
        for (String rows : matrix) {
            for (int i = 0; i < rows.length(); i++) {
                if (rows.charAt(i) == '0') {
                    heights[i] = 0;
                } else {
                    heights[i]++;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }
	// 直方图最大矩形面积
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0, n = heights.length;
        for (int i = 0; i < n; i++) {
            int left = i - 1, right = i + 1;
            if ( n * heights[i] > maxArea) {
                while (left >= 0 && heights[left] >= heights[i]) left--;
                while (right < n && heights[right] >= heights[i]) right++;
                maxArea = Math.max(maxArea, (right - left - 1) * heights[i]);
            }
        }
        return maxArea;
    }
}
```

* 时间复杂度：$O(n^3)$
* 空间复杂度：$O(n)$
