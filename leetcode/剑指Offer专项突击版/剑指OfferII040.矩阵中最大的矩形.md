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

