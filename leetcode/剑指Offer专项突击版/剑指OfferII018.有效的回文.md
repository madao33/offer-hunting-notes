# [剑指OfferII018.有效的回文](https://leetcode-cn.com/problems/XltzEq/)

难度：简单

给定一个字符串 `s` ，验证 `s` 是否是 **回文串** ，只考虑字母和数字字符，可以忽略字母的大小写。

本题中，将空字符串定义为有效的 **回文串** 。

 

**示例 1:**

```
输入: s = "A man, a plan, a canal: Panama"
输出: true
解释："amanaplanacanalpanama" 是回文串
```

**示例 2:**

```
输入: s = "race a car"
输出: false
解释："raceacar" 不是回文串
```

 **提示：**

- `1 <= s.length <= 2 * 105`
- 字符串 `s` 由 ASCII 字符组成

## 题解

一个比较简单的思想就是分别在头和尾设置双指针，如果头尾指针指向的字符不是字母就跳过，如果是字母，就将其都转换为小写，然后判断是否相等，不等就直接返回`false`，直到头尾指针相遇，表示判断完毕，字符串为回文串

```java
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while(left  <= right) {
            if(!Character.isLetterOrDigit(s.charAt(left))) left++;
            else if(!Character.isLetterOrDigit(s.charAt(right))) right--;
            else {
                char char1 = Character.toLowerCase(s.charAt(left++));
                char char2 = Character.toLowerCase(s.charAt(right--));
                if(char1 != char2) return false;
            }
        }
        return true;       
        }
}
```

* 时间复杂度： $O(n)$
* 空间复杂度：$O(1)$