# [剑指OfferII016.不含重复字符的最长子字符串](https://leetcode-cn.com/problems/wtcaE1/)

## 题目

给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度。

 示例 1:

```
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子字符串是 "abc"，所以其长度为 3。
```

示例 2:

```
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子字符串是 "b"，所以其长度为 1。
```

示例 3:

```
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```

示例 4:

```
输入: s = ""
输出: 0
```


提示：

* `0 <= s.length <= 5 * 104`
* `s` 由英文字母、数字、符号和空格组成

## 题解

这一题同[剑指 OfferII014.字符串中的变位词](剑指OfferII014.字符串中的变位词.md)也仅有些许变化，同样是统计字符重复次数，超过1次的重复字符就左指针右移，然后计算子串最大长度

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), start = 0, end = 0;
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;
        while(end < n) {
            char cur = s.charAt(end++);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            while(map.get(cur) > 1) {
                char pre = s.charAt(start++);
                map.put(pre, map.get(pre) - 1);
            }
            ans = Math.max(ans, end - start);
        }
        return ans;
    }
}
```

