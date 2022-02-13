# [剑指OfferII019.最多删除一个字符得到回文](https://leetcode-cn.com/problems/RQku0D/)

难度简单21收藏分享切换为英文接收动态反馈

给定一个非空字符串 `s`，请判断如果 **最多** 从字符串中删除一个字符能否得到一个回文字符串。

 **示例 1:**

```
输入: s = "aba"
输出: true
```

**示例 2:**

```
输入: s = "abca"
输出: true
解释: 可以删除 "c" 字符 或者 "b" 字符
```

**示例 3:**

```
输入: s = "abc"
输出: false
```

**提示:**

- `1 <= s.length <= 105`
- `s` 由小写英文字母组成

## 题解

和[剑指OfferII018.有效的回文](剑指OfferII018.有效的回文.md)有很多类似的思想，同样也是设置双指针从头尾分别查看字母是否相同，如果有不同的可以去掉左边的字符或者去掉右边的字符再次判断剩下的是否为回文字符串

```java
class Solution {
    public boolean validPalindrome(String s) {
        for(int left = 0, right = s.length() - 1; left <= right; left++, right--) {
            if(s.charAt(left) != s.charAt(right))
                return isPalindrome(s, left+1, right) || isPalindrome(s, left, right - 1);
        }
        return true;
    }

    public boolean isPalindrome(String s, int left, int right) {
        while(left <= right) {
            if(s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(1)$