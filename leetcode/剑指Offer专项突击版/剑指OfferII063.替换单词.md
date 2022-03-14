#### [剑指OfferII063.替换单词](https://leetcode-cn.com/problems/UhWRSj/)

难度中等17收藏分享切换为英文接收动态反馈

在英语中，有一个叫做 `词根(root)` 的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 `继承词(successor)`。例如，词根`an`，跟随着单词 `other`(其他)，可以形成新的单词 `another`(另一个)。

现在，给定一个由许多词根组成的词典和一个句子，需要将句子中的所有`继承词`用`词根`替换掉。如果`继承词`有许多可以形成它的`词根`，则用最短的词根替换它。

需要输出替换之后的句子。

 

**示例 1：**

```
输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
输出："the cat was rat by the bat"
```

**示例 2：**

```
输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
输出："a a b c"
```

**示例 3：**

```
输入：dictionary = ["a", "aa", "aaa", "aaaa"], sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
输出："a a a a a a a a bbb baba a"
```

**示例 4：**

```
输入：dictionary = ["catt","cat","bat","rat"], sentence = "the cattle was rattled by the battery"
输出："the cat was rat by the bat"
```

**示例 5：**

```
输入：dictionary = ["ac","ab"], sentence = "it is abnormal that this solution is accepted"
输出："it is ab that this solution is ac"
```

 

**提示：**

- `1 <= dictionary.length <= 1000`
- `1 <= dictionary[i].length <= 100`
- `dictionary[i]` 仅由小写字母组成。
- `1 <= sentence.length <= 10^6`
- `sentence` 仅由小写字母和空格组成。
- `sentence` 中单词的总量在范围 `[1, 1000]` 内。
- `sentence` 中每个单词的长度在范围 `[1, 1000]` 内。
- `sentence` 中单词之间由一个空格隔开。
- `sentence` 没有前导或尾随空格。

## 题解

使用前缀树实现单子前缀的替换，需要注意，再查找的时候，首先找到对应的结点，然后再查看是否到达前缀单词结尾，如果到了结尾，就返回单词长度；否则返回-1

```java
class Solution {

    static class Trie {
        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null)
                    node.children[index] = new Trie();
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public int startsWidth(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                node = node.children[index];
                if (node == null)
                    return -1;
                if (node.isEnd)
                    return i + 1;
            }
            return -1;
        }
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Trie root = new Trie();
        for (String word : dictionary)
            root.insert(word);

        String[] words = sentence.split(" ");
        StringBuilder ans = new StringBuilder();
        for (int i = 0, n = words.length; i < n; i++) {
            if (root.startsWidth(words[i]) > 0) {
                ans.append(words[i], 0, root.startsWidth(words[i]));
            } else {
                ans.append(words[i]);
            }
            ans.append(' ');
        }
        ans.deleteCharAt(ans.length() - 1);
        return ans.toString();
    }
}
```

