# [剑指OfferII032.有效的变位词](https://leetcode-cn.com/problems/dKk3P7/)

难度简单

给定两个字符串 `s` 和 `t` ，编写一个函数来判断它们是不是一组变位词（字母异位词）。

**注意：**若 `*s*` 和 `*t*` 中每个字符出现的次数都相同且**字符顺序不完全相同**，则称 `*s*` 和 `*t*` 互为变位词（字母异位词）。

 

**示例 1:**

```
输入: s = "anagram", t = "nagaram"
输出: true
```

**示例 2:**

```
输入: s = "rat", t = "car"
输出: false
```

**示例 3:**

```
输入: s = "a", t = "a"
输出: false
```

 

**提示:**

- `1 <= s.length, t.length <= 5 * 104`
- `s` and `t` 仅包含小写字母

##  题解

因为比较字符串是否每个字符出现的次数相同且字符顺序完全不同，这样可以称为变位词，首先可以明确两点：

* 变位词字符串长度相同
* 变位词字符不能相等，即`euqals`的结果为`false`

然后可以设置一个长度为26的数组，对于第一个字符串每个字母，出现一次自增一次，而对于第二个字符串的字符，出现一次减少一次，每次减少的时候判断是否小于0，如果小于0，则返回`false`，最后返回`true`

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        // 长度不等，不是变位词
        if (s.length() != t.length() || s.equals(t)) 
            return false;
        
        int[] nums = new int[26];
        for (int i = 0; i < s.length(); i++) nums[s.charAt(i) - 'a']++;

        for (int i = 0; i < t.length(); i++) {
            nums[t.charAt(i) - 'a']--;
            if(nums[t.charAt(i) - 'a'] < 0)
                return false;
        }

        return true;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(26)$