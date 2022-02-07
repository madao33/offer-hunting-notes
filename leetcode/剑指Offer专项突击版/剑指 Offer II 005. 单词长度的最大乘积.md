# [剑指 Offer II 005. 单词长度的最大乘积](https://leetcode-cn.com/problems/aseY1I/)

## 题目

给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。

 示例 1:

```
输入: words = ["abcw","baz","foo","bar","fxyz","abcdef"]
输出: 16 
解释: 这两个单词为 "abcw", "fxyz"。它们不包含相同字符，且长度的乘积最大。
```

示例 2:

```
输入: words = ["a","ab","abc","d","cd","bcd","abcd"]
输出: 4 
解释: 这两个单词为 "ab", "cd"。
```

示例 3:

```
输入: words = ["a","aa","aaa","aaaa"]
输出: 0 
解释: 不存在这样的两个单词。
```

## 题解

将每个单次对应的字母是否存在用`0/1`表示，然后逻辑与判断两两之间是否存在相同字符，不存在就计算其字符串长度积，保留最大的一个

```java
class Solution {
    public int maxProduct(String[] words) {
        int[] alph = new int[words.length];
        for(int i = 0; i < words.length; i++) {
            for(char c : words[i].toCharArray()) alph[i] = alph[i] | 1 << (c - 'a');
        }
        int max = 0;
        for(int i = 0; i < words.length-1; i++){
            for(int j = i+1; j < words.length; j++) {
                if((alph[i] & alph[j]) == 0) max = Math.max(max, words[i].length() * words[j].length());
            }
        }
        return max;
    }
}
```

