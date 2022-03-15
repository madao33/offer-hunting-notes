# [剑指OfferII117.相似的字符串](https://leetcode-cn.com/problems/H6lPxb/)

难度困难8收藏分享切换为英文接收动态反馈

如果交换字符串 `X` 中的两个不同位置的字母，使得它和字符串 `Y` 相等，那么称 `X` 和 `Y` 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。

例如，`"tars"` 和 `"rats"` 是相似的 (交换 `0` 与 `2` 的位置)； `"rats"` 和 `"arts"` 也是相似的，但是 `"star"` 不与 `"tars"`，`"rats"`，或 `"arts"` 相似。

总之，它们通过相似性形成了两个关联组：`{"tars", "rats", "arts"}` 和 `{"star"}`。注意，`"tars"` 和 `"arts"` 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。

给定一个字符串列表 `strs`。列表中的每个字符串都是 `strs` 中其它所有字符串的一个 **字母异位词** 。请问 `strs` 中有多少个相似字符串组？

**字母异位词（anagram）**，一种把某个字符串的字母的位置（顺序）加以改换所形成的新词。

 

**示例 1：**

```
输入：strs = ["tars","rats","arts","star"]
输出：2
```

**示例 2：**

```
输入：strs = ["omv","ovm"]
输出：1
```

 

**提示：**

- `1 <= strs.length <= 300`
- `1 <= strs[i].length <= 300`
- `strs[i]` 只包含小写字母。
- `strs` 中的所有单词都具有相同的长度，且是彼此的字母异位词。

## 题解

图BFS和并查集

```java
class Solution {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        int cnt = n;
        int[] fathers = new int[n];
        for(int i = 0; i < n; ++i){
            fathers[i] = i;
        }
        for(int i = 0; i < n; ++i){
            for(int j = i+1; j < n; ++j){
                if(isSimilar(strs[i], strs[j]) && union(fathers, i, j)){
                    cnt--;
                }
            }
        }
        return cnt;
    }

    public boolean isSimilar(String s1, String s2){
        int cnt = 0;
        for(int i = 0; i < s1.length(); ++i){
            if(s1.charAt(i) != s2.charAt(i)) cnt++;
        }
        return cnt<=2;
    }

    public boolean union(int[] fathers, int i, int j){
        int a = findFather(fathers, i);
        int b = findFather(fathers, j);
        if(a != b){
            fathers[a] = b;
            return true;
        }
        return false;
    }

    public int findFather(int[] fathers, int i){
        if(fathers[i] != i){
            fathers[i] = findFather(fathers, fathers[i]);
        }
        return fathers[i];
    }
}
```

