#### [剑指OfferII066.单词之和](https://leetcode-cn.com/problems/z1R5dt/)

难度中等12收藏分享切换为英文接收动态反馈

实现一个 `MapSum` 类，支持两个方法，`insert` 和 `sum`：

- `MapSum()` 初始化 `MapSum` 对象
- `void insert(String key, int val)` 插入 `key-val` 键值对，字符串表示键 `key` ，整数表示值 `val` 。如果键 `key` 已经存在，那么原来的键值对将被替代成新的键值对。
- `int sum(string prefix)` 返回所有以该前缀 `prefix` 开头的键 `key` 的值的总和。

 

**示例：**

```
输入：
inputs = ["MapSum", "insert", "sum", "insert", "sum"]
inputs = [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
输出：
[null, null, 3, null, 5]

解释：
MapSum mapSum = new MapSum();
mapSum.insert("apple", 3);  
mapSum.sum("ap");           // return 3 (apple = 3)
mapSum.insert("app", 2);    
mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
```

 

**提示：**

- `1 <= key.length, prefix.length <= 50`
- `key` 和 `prefix` 仅由小写英文字母组成
- `1 <= val <= 1000`
- 最多调用 `50` 次 `insert` 和 `sum`

## 题解

创建一个前缀树，将`isEnd`修改为`int value`保存值，插入的时候将叶子结点的`value`修改为插入的值，然后查找得到以查找词为前缀的结点，统计该结点所有子节点的`value`值

```java
class MapSum {

    static class Trie {
        Trie[] children;
        int value;

        public Trie() {
            children = new Trie[26];
            value = 0;
        }

        public void insert(String word, int value) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.value = value;
        }

        public Trie searchPrefix(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                int index = prefix.charAt(i) - 'a';
                if (node.children[index] == null)
                    return null;
                node = node.children[index];
            }
            return node;
        }

        public int dfs(Trie node) {
            if (node == null) return 0;
            Queue<Trie> queue = new LinkedList<>();
            queue.offer(node);
            int sum = 0;
            while(!queue.isEmpty()) {
                Trie temp = queue.poll();
                sum += temp.value;
                for (int i = 0; i < 26; i++) {
                    if (temp.children[i] != null)
                        queue.offer(temp.children[i]);
                }
            }
            return sum;
        }
    }
    Trie root;
    public MapSum() {
        root = new Trie();
    }

    public void insert(String key, int val) {
        root.insert(key, val);
    }

    public int sum(String prefix) {
        return root.dfs(root.searchPrefix(prefix));
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
```

