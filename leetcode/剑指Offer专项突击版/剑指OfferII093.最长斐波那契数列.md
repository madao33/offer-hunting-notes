# [剑指OfferII093.最长斐波那契数列](https://leetcode-cn.com/problems/Q91FMA/)

难度中等24收藏分享切换为英文接收动态反馈

如果序列 `X_1, X_2, ..., X_n` 满足下列条件，就说它是 *斐波那契式* 的：

- `n >= 3`
- 对于所有 `i + 2 <= n`，都有 `X_i + X_{i+1} = X_{i+2}`

给定一个**严格递增**的正整数数组形成序列 `arr` ，找到 `arr` 中最长的斐波那契式的子序列的长度。如果一个不存在，返回 0 。

*（回想一下，子序列是从原序列 `arr` 中派生出来的，它从 `arr` 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， `[3, 5, 8]` 是 `[3, 4, 5, 6, 7, 8]` 的一个子序列）*

 



**示例 1：**

```
输入: arr = [1,2,3,4,5,6,7,8]
输出: 5
解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
```

**示例 2：**

```
输入: arr = [1,3,7,11,12,14,18]
输出: 3
解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
```

 

**提示：**

- `3 <= arr.length <= 1000`
- `1 <= arr[i] < arr[i + 1] <= 10^9`

## 题解

可以明确一点就是斐波那切数列前两个数的和就等于第三个数，那么反过来可以推理，如果知道后面两个数，也可以推得第一个数

所以定义一个二维数组`dp[n][n]`用来表示斐波那切数列子序列的长度，例如`dp[i][j]`就是以`arr`第`j,i`两数结尾的斐波拉切数列的长度，如果`arr`中存在第`k`个数刚好等于`i,j`指向数的差，也就是`arr[k]=arr[i]-arr[j]`，那么可以更新`dp[i][j]= dp[j][k]+1`

同时也可以得到如果`arr[k]>=arr[j]`，可以得知之前的数已经不存在可以组成斐波拉切数列的项了，可以剪枝返回

```java
class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) hashMap.put(arr[i], i);
        int ans = 0;
        for (int i = 2; i < n; i++) {
            for (int j = i - 1; j > 0; j--) {
                int pre = arr[i] - arr[j];
                if (pre >= arr[j]) break;
                if (hashMap.containsKey(pre)) {
                    int preIndex = hashMap.get(pre);
                    dp[i][j] = Math.max(2, dp[j][preIndex]) + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }
}
```

