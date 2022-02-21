#### [剑指OfferII039.直方图最大矩形面积](https://leetcode-cn.com/problems/0ynMMM/)

难度困难

给定非负整数数组 `heights` ，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 `1` 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

 

**示例 1:**

![img](https://assets.leetcode.com/uploads/2021/01/04/histogram.jpg)

```
输入：heights = [2,1,5,6,2,3]
输出：10
解释：最大的矩形为图中红色区域，面积为 10
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2021/01/04/histogram-1.jpg)

```
输入： heights = [2,4]
输出： 4
```

 

**提示：**

- `1 <= heights.length <=105`
- `0 <= heights[i] <= 104`

## 题解

双指针，遍历每一个柱子，其左边界为左边比它小的元素的索引，其右边界为右边比它小的元素的索引
宽度=右边界-左边界-1， 高度即当前柱子高度；遍历所有柱子，不断更新最大面积

```java
class Solution {
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

* 时间复杂度：$O(n^2)$
* 空间复杂度：$O(1)$