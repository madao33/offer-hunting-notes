# 说明

本学习计划适用于想为技术面试做准备但不确定应该聚焦于哪些题目的用户。学习计划中的题目都是经过精心挑选的，Level 1和 Level 2 学习计划是为初级用户和中级用户准备的，题目覆盖了大多数中层公司面试时所必需的数据结构和算法，Level 3 学习计划则是为准备面试顶级公司的用户准备的。

[toc]

# Day1

## [202. 快乐数](https://leetcode.cn/problems/happy-number/)

难度简单

编写一个算法来判断一个数 `n` 是不是快乐数。

**「快乐数」** 定义为：

- 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
- 然后重复这个过程直到这个数变为 1，也可能是 **无限循环** 但始终变不到 1。
- 如果这个过程 **结果为** 1，那么这个数就是快乐数。

如果 `n` 是 *快乐数* 就返回 `true` ；不是，则返回 `false` 。

**示例 1：**

```
输入：n = 19
输出：true
解释：
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
```

**示例 2：**

```
输入：n = 2
输出：false
```

**提示：**

- $ 1\leq n \leq 2^{31} - 1$

**题解**

获取下一个数，通过取余和整除获取该数的每一位，然后计算每一位的平方和得到

关键的问题是如何判断循环

**集合查重**

第一种方法是可以使用`set`来判断当前数之前是否出现过

```java
public class Solution {
    public int getNext(int n) {
        int res = 0;
        while(n != 0) {
            int temp = n % 10;
            res += temp * temp;
            n /= 10;
        }
        return res;
    }
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();
        while(n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNext(n);
        }
        
        return n == 1;
    }
}
```

* 时间复杂度：$O(\log n)$
* 空间复杂度：$O(\log n)$

![image-20220730211257393](https://madao33-static.oss-cn-hangzhou.aliyuncs.com/madao33blog/post/leetcode/image-20220730211257393.png)

**快慢指针**

类似于循环链表的判断，可以通过快慢指针的方式来实现

```java
public class Solution {
    public int getNext(int n) {
        int res = 0;
        while(n != 0) {
            int temp = n % 10;
            res += temp * temp;
            n /= 10;
        }
        return res;
    }
    public boolean isHappy(int n) {
        int fast = n, slow = n;
        do{
            slow = getNext(slow);
            fast = getNext(fast);
            fast = getNext(fast);
        } while(fast != slow);
        return fast == 1;
    }
}
```

* 时间复杂度：$O(\log n)$
* 空间复杂度：$O(1)$

![image-20220730211137643](https://madao33-static.oss-cn-hangzhou.aliyuncs.com/madao33blog/post/leetcode/image-20220730211137643.png)

## [54. 螺旋矩阵](https://leetcode.cn/problems/spiral-matrix/)

难度中等1159

给你一个 `m` 行 `n` 列的矩阵 `matrix` ，请按照 **顺时针螺旋顺序** ，返回矩阵中的所有元素。

**示例 1：**

![img](https://madao33-static.oss-cn-hangzhou.aliyuncs.com/madao33blog/post/leetcode/spiral1.jpg)

```
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
```

**示例 2：**

![img](https://madao33-static.oss-cn-hangzhou.aliyuncs.com/madao33blog/post/leetcode/spiral.jpg)

```
输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
```

**提示：**

- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= m, n <= 10`
- `-100 <= matrix[i][j] <= 100`

**题解**

可以将矩阵看成若干层，首先输出最外层的元素，其次输出次外层的元素，直到输出最内层的元素。

![fig1](https://madao33-static.oss-cn-hangzhou.aliyuncs.com/madao33blog/post/leetcode/54_fig1.png)



```java
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null)
            return res;
        int n = matrix.length, m = matrix[0].length;
        int left = 0, right = m - 1, top = 0, buttom = n - 1;
        while (left <= right && top <= buttom) {
            for (int j = left; j <= right; j++)
                res.add(matrix[top][j]);
            for (int i = top + 1; i <= buttom; i++)
                res.add(matrix[i][right]);

            if (left < right && top < buttom) {
                for (int j = right - 1; j > left; j--)
                    res.add(matrix[buttom][j]);
                for (int i = buttom; i > top; i--)
                    res.add(matrix[i][left]);
            }
            left++;
            top++;
            right--;
            buttom--;
        }
        return res;
    }
}
```

* 时间复杂度：$O(mn)$
* 空间复杂度：$O(1)$

## [1706. 球会落何处](https://leetcode.cn/problems/where-will-the-ball-fall/)

难度中等145

用一个大小为 `m x n` 的二维网格 `grid` 表示一个箱子。你有 `n` 颗球。箱子的顶部和底部都是开着的。

箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。

- 将球导向右侧的挡板跨过左上角和右下角，在网格中用 `1` 表示。
- 将球导向左侧的挡板跨过右上角和左下角，在网格中用 `-1` 表示。

在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。如果球恰好卡在两块挡板之间的 "V" 形图案，或者被一块挡导向到箱子的任意一侧边上，就会卡住。

返回一个大小为 `n` 的数组 `answer` ，其中 `answer[i]` 是球放在顶部的第 `i` 列后从底部掉出来的那一列对应的下标，如果球卡在盒子里，则返回 `-1` 。

 

**示例 1：**

**![img](https://madao33-static.oss-cn-hangzhou.aliyuncs.com/madao33blog/post/leetcode/ball.jpg)**

```
输入：grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
输出：[1,-1,-1,-1,-1]
解释：示例如图：
b0 球开始放在第 0 列上，最终从箱子底部第 1 列掉出。
b1 球开始放在第 1 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
b2 球开始放在第 2 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
b3 球开始放在第 3 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
b4 球开始放在第 4 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
```

**示例 2：**

```
输入：grid = [[-1]]
输出：[-1]
解释：球被卡在箱子左侧边上。
```

**示例 3：**

```
输入：grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
输出：[0,1,2,3,4,-1]
```

 

**提示：**

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 100`
- `grid[i][j]` 为 `1` 或 `-1`

**题解**

对于每个球，依次判断位置，如果碰到侧边或者V型则球会停止移动

* 侧边也就是球所在的列越界
* 碰到V型，也就是上次移动方向和当前格子移动方向相反

```java
class Solution {
    public int[] findBall(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[] ans = new int[m];
        for (int j = 0; j < m; j++) {
            int col = j;  // 球的初始列
            for (int i = 0; i < n; i++) {
                int dir = grid[i][col];
                col += dir;  // 移动球
                if (col < 0 || col == m || grid[i][col] != dir) {  // 到达侧边或 V 形
                    col = -1;
                    break;
                }
            }
            ans[j] = col;  // col >= 0 为成功到达底部
        }
        return ans;
    }
}
```

* 时间复杂度：$O(n \times m)$
* 空间复杂度：$O(1)$

![image-20220730221757284](https://madao33-static.oss-cn-hangzhou.aliyuncs.com/madao33blog/post/leetcode/image-20220730221757284.png)