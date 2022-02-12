# [剑指OfferII015.字符串中的所有变位词](https://leetcode-cn.com/problems/VabMRr/)

## 题目

给定两个字符串 s 和 p，找到 s 中所有 p 的 变位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

变位词 指字母相同，但排列不同的字符串。

 示例 1:

```
输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的变位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的变位词。
```

 示例 2:

```
输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的变位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的变位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的变位词。
```


提示:

* `1 <= s.length, p.length <= 3 * 104`
* `s` 和 `p` 仅包含小写字母

## 题解

同样和上一题的思路相同，不过不同的是需要将符合要求的窗口的起始索引添加到`List<>`数组中，稍微修改上一题的代码

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        int sl = s.length(), pl = p.length();
        if(sl < pl) return res;

        int[] cnt = new int[26];
        for(int i = 0; i < pl; i++) {
            --cnt[p.charAt(i) - 'a'];
            ++cnt[s.charAt(i) - 'a'];
        }
        int diff = 0;
        for(int c : cnt) {
            diff += (c == 0 ? 0 : 1);
        }
        if(diff == 0) res.add(0);
        for(int i = 0; i < sl - pl; i++) {
            int x = s.charAt(i + pl) - 'a', y = s.charAt(i) - 'a';
            // 如果进入窗口和出窗口的字符相同且滑动前匹配，那么滑动后也是匹配的
            if(x == y){
                if(diff==0) res.add(i + 1);
                continue;
            }
            if(cnt[x] == 0) ++diff;
            ++cnt[x];
            if(cnt[x] == 0) --diff;
            if(cnt[y] == 0) ++diff;
            --cnt[y];
            if(cnt[y] == 0) --diff;
            if(diff == 0) res.add(i + 1);
        }
        return res;
    }
}
```

