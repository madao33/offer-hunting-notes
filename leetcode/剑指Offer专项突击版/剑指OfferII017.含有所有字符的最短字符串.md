# [剑指OfferII017.含有所有字符的最短字符串](https://leetcode-cn.com/problems/M1oyTv/)

难度困难20收藏分享切换为英文接收动态反馈

给定两个字符串 `s` 和 `t` 。返回 `s` 中包含 `t` 的所有字符的最短子字符串。如果 `s` 中不存在符合条件的子字符串，则返回空字符串 `""` 。

如果 `s` 中存在多个符合条件的子字符串，返回任意一个。

 

**注意：** 对于 `t` 中重复字符，我们寻找的子字符串中该字符数量必须不少于 `t` 中该字符数量。

 

**示例 1：**

```
输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC" 
解释：最短子字符串 "BANC" 包含了字符串 t 的所有字符 'A'、'B'、'C'
```

**示例 2：**

```
输入：s = "a", t = "a"
输出："a"
```

**示例 3：**

```
输入：s = "a", t = "aa"
输出：""
解释：t 中两个字符 'a' 均应包含在 s 的子串中，因此没有符合条件的子字符串，返回空字符串。
```

 

**提示：**

- `1 <= s.length, t.length <= 105`
- `s` 和 `t` 由英文字母组成

 

**进阶：**你能设计一个在 `o(n)` 时间内解决此问题的算法吗？

## 题解



```java
public String contains(String s1, String s2) {
    int l = 0, r = 0;
    int len = s1.length();
    //统计要匹配的字符串字符出现的次数
    Map<Character, Integer> cnt2 = new HashMap<>();
    for (int i = 0; i < s2.length(); i++) {
        cnt2.put(s2.charAt(i), cnt2.getOrDefault(s2.charAt(i), 0) + 1);
    }
    //记录窗口内字符出现次数
    Map<Character, Integer> cnt = new HashMap<>();
    String ans = "";
    while (l <= r && r < len) {
        //窗口扩大，将右侧字符计数
        cnt.put(s1.charAt(r), cnt.getOrDefault(s1.charAt(r), 0) + 1);
        while (match(cnt, cnt2)) {
            //找到匹配字符串，开始讲窗口向右滑动，左边字符计数-1
            if (ans.length() == 0 || (r - l) < ans.length()) {
                ans = s1.substring(l, r + 1);
            }
            cnt.put(s1.charAt(l), cnt.getOrDefault(s1.charAt(l), 0) - 1);
            l++;
        }
        r++;
    }
    return ans;
}

private boolean match(Map<Character, Integer> cnt, Map<Character, Integer> cnt2) {
    for (Map.Entry<Character, Integer> characterIntegerEntry : cnt2.entrySet()) {
        Character key = characterIntegerEntry.getKey();
        if (cnt.getOrDefault(key, 0) < characterIntegerEntry.getValue()) {
            return false;
        }
    }
    return true;
}

```

