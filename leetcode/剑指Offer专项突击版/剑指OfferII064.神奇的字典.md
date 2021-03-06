#### [剑指OfferII064.神奇的字典](https://leetcode-cn.com/problems/US1pGT/)

难度中等16收藏分享切换为英文接收动态反馈

设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 **互不相同** 。 如果给出一个单词，请判定能否只将这个单词中**一个**字母换成另一个字母，使得所形成的新单词存在于已构建的神奇字典中。

实现 `MagicDictionary` 类：

- `MagicDictionary()` 初始化对象
- `void buildDict(String[] dictionary)` 使用字符串数组 `dictionary` 设定该数据结构，`dictionary` 中的字符串互不相同
- `bool search(String searchWord)` 给定一个字符串 `searchWord` ，判定能否只将字符串中 **一个** 字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 `true` ；否则，返回 `false` 。

 

**示例：**

```
输入
inputs = ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
inputs = [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
输出
[null, null, false, true, false, false]

解释
MagicDictionary magicDictionary = new MagicDictionary();
magicDictionary.buildDict(["hello", "leetcode"]);
magicDictionary.search("hello"); // 返回 False
magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
magicDictionary.search("hell"); // 返回 False
magicDictionary.search("leetcoded"); // 返回 False
```

 

**提示：**

- `1 <= dictionary.length <= 100`
- `1 <= dictionary[i].length <= 100`
- `dictionary[i]` 仅由小写英文字母组成
- `dictionary` 中的所有字符串 **互不相同**
- `1 <= searchWord.length <= 100`
- `searchWord` 仅由小写英文字母组成
- `buildDict` 仅在 `search` 之前调用一次
- 最多调用 `100` 次 `search`

## 题解

可以将变位词添加到一个`HashMap`中，例如`apple`有变位词`*pple, a*ple, ap*le, app*e, appl*`，然后判断搜索的单词的变位词是否和变位词字典相等，且单词不与字典词完全相等，则返回`false`，否则返回`true`

```java
class MagicDictionary {

    Set<String> words;
    Map<String, Integer> neighborCount;

    public MagicDictionary() {
        words = new HashSet<>();
        neighborCount = new HashMap<>();
    }
    
    public String[] getNeighbors(String word) {
        String[] neighbors = new String[word.length()];
        StringBuilder str = new StringBuilder(word);
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            str.setCharAt(i, '*');
            neighbors[i] = str.toString();
            str.setCharAt(i, ch);
        }
        return neighbors;
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            words.add(word);
            for (String neighbor : getNeighbors(word)) {
                neighborCount.put(neighbor, neighborCount.getOrDefault(neighbor, 0) + 1);
            }
        }
    }
    
    public boolean search(String searchWord) {
        for (String neighbor : getNeighbors(searchWord)) {
            int searchNum = neighborCount.getOrDefault(neighbor, 0);
            if (searchNum > 1 || searchNum == 1 && !words.contains(searchWord))
                return true;
        }
        return false;
    }
}
```

