# [剑指OfferII071.按权重生成随机数](https://leetcode-cn.com/problems/cuyjEf/)

难度中等9收藏分享切换为英文接收动态反馈

给定一个正整数数组 `w` ，其中 `w[i]` 代表下标 `i` 的权重（下标从 `0` 开始），请写一个函数 `pickIndex` ，它可以随机地获取下标 `i`，选取下标 `i` 的概率与 `w[i]` 成正比。



例如，对于 `w = [1, 3]`，挑选下标 `0` 的概率为 `1 / (1 + 3) = 0.25` （即，25%），而选取下标 `1` 的概率为 `3 / (1 + 3) = 0.75`（即，75%）。

也就是说，选取下标 `i` 的概率为 `w[i] / sum(w)` 。

 

**示例 1：**

```
输入：
inputs = ["Solution","pickIndex"]
inputs = [[[1]],[]]
输出：
[null,0]
解释：
Solution solution = new Solution([1]);
solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
```

**示例 2：**

```
输入：
inputs = ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
inputs = [[[1,3]],[],[],[],[],[]]
输出：
[null,1,1,1,1,0]
解释：
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。

由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
诸若此类。
```

 

**提示：**

- `1 <= w.length <= 10000`
- `1 <= w[i] <= 10^5`
- `pickIndex` 将被调用不超过 `10000` 次

## 题解

前缀和加二分查找，首先计算前缀和数组，例如对于`w=[3, 1, 2, 4]`，得到前缀和数组`pre=[3, 4, 6, 10]`，然后生成随机数`x=Math.random *pre[w.length-1] `，然后查找`x`在前缀和数组中的位置或者插入位置，就是返回生成的随机数

```java
class Solution {
    int[] pre;
    int total;

    public Solution(int[] w) {
        pre = new int[w.length];
        pre[0] = w[0];
        for (int i = 1; i < w.length; i++)
            pre[i] = pre[i - 1] + w[i];
        total = pre[w.length-1];
    }
    
    public int pickIndex() {
        int x = (int) (Math.random() * total) + 1;
        return binarySearch(x);
    }

    private int binarySearch(int x) {
        int low = 0, high = pre.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (pre[mid] < x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
```

* 时间复杂度：$O(log(N))$
* 空间复杂度：$O(N)$

