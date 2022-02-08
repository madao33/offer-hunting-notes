# [剑指 Offer II 003. 前 n 个数字二进制中 1 的个数](https://leetcode-cn.com/problems/w3tCBm/)

## 题目

给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。

示例 1:

```
输入: n = 2
输出: [0,1,1]
解释: 
0 --> 0
1 --> 1
2 --> 10
```

示例 2:

```
输入: n = 5
输出: [0,1,1,2,1,2]
解释:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101
```


说明 :

* 0 <= n <= 105


进阶:

* 给出时间复杂度为 O(n*sizeof(integer)) 的解答非常容易。但你可以在线性时间 O(n) 内用一趟扫描做到吗？
* 要求算法的空间复杂度为 O(n) 。
* 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount ）来执行此操作。

## 题解

### Brian Kernighan 算法

利用Brian Kernighan算法：对于任意整数$x$，令$x \&= (x-1)$，该运算将 $x$ 的二进制表示的最后一个1变成0。对$x$重复该操作，直到$x$变成0，则该操作次数即为$x$的比特数

对于$0-n$个数依次进行以上算法得到一个$n+1$的数组

```java
class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            res[i] = countbit(i);
        }
        return res;
    }

    public int countbit(int n) {
        int count = 0;
        while(n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}
```

* 时间复杂度：$O(n\log n)$
* 空间复杂度：$O(1)$

