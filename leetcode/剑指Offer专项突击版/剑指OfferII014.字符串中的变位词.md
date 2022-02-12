# [剑指 Offer II 014. 字符串中的变位词](https://leetcode-cn.com/problems/MPnaiL/)

## 题目

给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。

换句话说，第一个字符串的排列之一是第二个字符串的 子串 。

 示例 1：

```
输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").
```

示例 2：

```
输入: s1= "ab" s2 = "eidboaoo"
输出: False
```


提示：

* `1 <= s1.length, s2.length <= 104`
* `s1` 和 `s2` 仅包含小写字母

## 题解

### 滑动窗口方法

利用滑动窗口比较字符串中是否存在一个子串，滑动窗口的大小和`s1`大小相当

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if(m > n) return false;
        int[] count1 = new int[26], count2 = new int[26];
        for(int i = 0; i < s1.length(); i++) { 
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }
        if(Arrays.equals(count1, count2)) return true;
        for(int i = m; i < n; i++) {
            count2[s2.charAt(i) - 'a']++;
            count2[s2.charAt(i-m) - 'a']--;
            if(Arrays.equals(count1, count2))
                return true;
        }
        return false;
    }
}
```

### 优化滑动窗口

虽然每次滑动窗口都只是变化了两个字符，但是上面的方法都比较了整个字符串，可以将这部分进行优化

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if(m > n) return false;
        int[] cnt = new int[26];
        for(int i = 0; i < m; ++i) {
            --cnt[s1.charAt(i) - 'a'];
            ++cnt[s2.charAt(i) - 'a'];
        }
        int diff = 0;
        for(int c : cnt) {
            diff += (c == 0 ? 0 : 1);
        }
        if(diff == 0)
            return true;
        for(int i = m; i < n; ++i) {
            int x = s2.charAt(i) - 'a', y = s2.charAt(i-m) - 'a';
            if(x == y) continue;
            if(cnt[x] == 0) ++diff;
            ++cnt[x];
            if(cnt[x] == 0) --diff; 
            if(cnt[y] == 0) ++diff;
            --cnt[y];
            if(cnt[y] == 0) --diff;
            if(diff == 0) return true;
        }
        return false;
    }
}
```

