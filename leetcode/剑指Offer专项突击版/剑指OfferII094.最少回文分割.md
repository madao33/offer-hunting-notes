#### [剑指OfferII094.最少回文分割](https://leetcode-cn.com/problems/omKAoA/)

难度困难14收藏分享切换为英文接收动态反馈

给定一个字符串 `s`，请将 `s` 分割成一些子串，使每个子串都是回文串。

返回符合要求的 **最少分割次数** 。

 

**示例 1：**

```
输入：s = "aab"
输出：1
解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
```

**示例 2：**

```
输入：s = "a"
输出：0
```

**示例 3：**

```
输入：s = "ab"
输出：1
```

 

**提示：**

- `1 <= s.length <= 2000`
- `s` 仅由小写英文字母组成

## 题解

对于字符串`0, ..., j, ..., i`如果存在`j~i`字符串时回文字符串，那么`dp[i]=min(dp[i], dp[j]+1)`

下面这种方式的时间复杂度过高，需要优化

```java
class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if (valid(s, j, i-1)) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n]-1;
    }

    public boolean valid(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }
}
```

