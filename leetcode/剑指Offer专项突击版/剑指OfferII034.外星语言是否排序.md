# [剑指OfferII034.外星语言是否排序](https://leetcode-cn.com/problems/lwyVBB/)

难度简单12收藏分享切换为英文接收动态反馈

某种外星语也使用英文小写字母，但可能顺序 `order` 不同。字母表的顺序（`order`）是一些小写字母的排列。

给定一组用外星语书写的单词 `words`，以及其字母表的顺序 `order`，只有当给定的单词在这种外星语中按字典序排列时，返回 `true`；否则，返回 `false`。

 

**示例 1：**

```
输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
输出：true
解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
```

**示例 2：**

```
输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
输出：false
解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
```

**示例 3：**

```
输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
输出：false
解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（更多信息）。
```

 

**提示：**

- `1 <= words.length <= 100`
- `1 <= words[i].length <= 20`
- `order.length == 26`
- 在 `words[i]` 和 `order` 中的所有字符都是英文小写字母。

## 题解

和之前的变位词判断一样，可以通过一个`int[26]`的数组暂存给定字母表的顺序，然后每次取两个字母判断是否按照字母表顺序，第一次判断取前两个，然后是第二个和第三个，直到遍历结束，其中有不符合字母表顺序的，直接返回`false`

判断两个字母是否符合给定的字母表顺序，遍历两个字母的公共长度部分，如果`A`字符串的字母顺序在`B`字符前，则直接返回`true`，如果在之后，则返回`false`，否则，继续判断公共部分，如果公共部分判断完毕，而`A`字符串还存在没有遍历的字母，那么返回`false`

```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] alph = new int[26];
        for (int i = 0; i < order.length(); i++)
            alph[order.charAt(i) - 'a'] = i + 1;
        for(int i = 0; i < words.length-1; i++) {
            if (!isAlph(words[i], words[i+1], alph))
                return false;
        }
        return true;
    }

    public boolean isAlph(String A, String B, int[] alph) {
        int index = 0;
        while(index < A.length() && index < B.length()) {
            if (alph[A.charAt(index) - 'a'] < alph[B.charAt(index) - 'a'])
                return true;
            if (alph[A.charAt(index) - 'a'] > alph[B.charAt(index) - 'a'])
                return false;
            index++;
        }
        if (index < A.length()) return false;
        return true;
    }
}
```

* 时间复杂度：$O(mn)$，其中$m$表示`words`数组的长度，$n$表示数组中最长的字符串长度
* 空间复杂度：$O(26)$，主要的辅助空间是长度$26$的一个辅助数组，用以判断字符顺序