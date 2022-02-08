# [剑指 Offer II 001. 整数除法](https://leetcode-cn.com/problems/xoh6Oh/)

## 题目

给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。

 注意：

整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231−1]。本题中，如果除法结果溢出，则返回 231 − 1


示例 1：

```
输入：a = 15, b = 2
输出：7
解释：15/2 = truncate(7.5) = 7
```

示例 2：

```
输入：a = 7, b = -3
输出：-2
解释：7/-3 = truncate(-2.33333..) = -2
```

示例 3：

```
输入：a = 0, b = 1
输出：0
```

示例 4：

```
输入：a = 1, b = 1
输出：1
```


提示:

* `-231 <= a, b <= 231 - 1`
* `b != 0`

## 题解

这一题目暂时还未理解思路，只有先抄一抄，之后标记来看

```c++
class Solution {
public:
    int divide(int a, int b) {
        if (a == INT_MIN && b == -1) {
            return INT_MAX;
        }
        int negative = 2;
        if (a > 0) {
            negative--;
            a = -a;
        }
        if (b > 0) {
            negative--;
            b = -b;
        }
        unsigned int ret = divideCore(a, b);
        return negative == 1 ? -ret : ret;
    }

    unsigned int divideCore(int a, int b) {
        int ret = 0;
        // 注意a, b都是负数，所以a <= b就是还可以继续除
        while (a <= b) {
            int value = b;
            unsigned int quo = 1;
            while (value >= 0xc0000000 && a <= value + value) {
                quo += quo;
                value += value;
            }
            ret += quo;
            a -= value;
        }
        return ret;
    }
};
```

