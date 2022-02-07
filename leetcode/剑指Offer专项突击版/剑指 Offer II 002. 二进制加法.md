# [剑指 Offer II 002. 二进制加法](https://leetcode-cn.com/problems/JFETK5/)

## 题目

给定两个 01 字符串 `a` 和 `b` ，请计算它们的和，并以二进制字符串的形式输出。

输入为 **非空** 字符串且只包含数字 `1` 和 `0`。

示例 1:

```
输入: a = "11", b = "10"
输出: "101"
```

示例 2:

```
输入: a = "1010", b = "1011"
输出: "10101"
```


提示：

* 每个字符串仅由字符 '0' 或 '1' 组成。
* 1 <= a.length, b.length <= 10^4
* 字符串如果不是 "0" ，就都不含前导零。

链接：https://leetcode-cn.com/problems/JFETK5

## 题解

思路就是按照竖列式的计算规则，每个相同的数位进行求和计算，计算的结果先反向存储在结果数组中，然后再反向输出，注意保存每一位计算结果的时候需要将其转换为`char`类型

```java
class Solution {
    public String addBinary(String a, String b) {
        StringBuffer ans = new StringBuffer();
        int n = Math.max(a.length(), b.length()), carry = 0;
        for(int i = 0; i < n; i++) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            // 需要强制转换为char
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if(carry != 0)
            ans.append('1');
            
        // 将结果反向
        ans.reverse();
        return ans.toString();
    }
}
```



