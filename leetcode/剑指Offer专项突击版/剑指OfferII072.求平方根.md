# [剑指OfferII072.求平方根](https://leetcode-cn.com/problems/jJ0w9p/)

难度简单11收藏分享切换为英文接收动态反馈

给定一个非负整数 `x` ，计算并返回 `x` 的平方根，即实现 `int sqrt(int x)` 函数。

正数的平方根有两个，只输出其中的正数平方根。

如果平方根不是整数，输出只保留整数的部分，小数部分将被舍去。

 

**示例 1:**

```
输入: x = 4
输出: 2
```

**示例 2:**

```
输入: x = 8
输出: 2
解释: 8 的平方根是 2.82842...，由于小数部分将被舍去，所以返回 2
```

 

**提示:**

- `0 <= x <= 231 - 1`

## 题解

同样使用二分查找，需要注意的是判断使用`x/mid>=mid`比`mid*mid<=x`要好一些，可以防止乘法导致的溢出

```java
class Solution {
    public int mySqrt(int x) {
        int left = 0, right = x;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (x/mid >= mid) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
```

