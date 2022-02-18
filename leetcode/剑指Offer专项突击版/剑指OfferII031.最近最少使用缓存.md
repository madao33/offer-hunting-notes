#### [剑指OfferII031.最近最少使用缓存](https://leetcode-cn.com/problems/OrIXps/)

难度中等

运用所掌握的数据结构，设计和实现一个 [LRU (Least Recently Used，最近最少使用) 缓存机制](https://baike.baidu.com/item/LRU) 。

实现 `LRUCache` 类：

- `LRUCache(int capacity)` 以正整数作为容量 `capacity` 初始化 LRU 缓存
- `int get(int key)` 如果关键字 `key` 存在于缓存中，则返回关键字的值，否则返回 `-1` 。
- `void put(int key, int value)` 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

 

**示例：**

```
输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4
```

 

**提示：**

- `1 <= capacity <= 3000`
- `0 <= key <= 10000`
- `0 <= value <= 105`
- 最多调用 `2 * 105` 次 `get` 和 `put`

## 题解

暂时没有理解

```java
public class LRUCache {
    private final int capacity;
    private final LinkedList list;
    private final Map<Integer, LinkedListNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        list = new LinkedList();
        map = new HashMap<>(capacity);
    }

    public int get(int key) {
        // 在 map 中根据 key 获取在链表中的 node
        LinkedListNode node = map.getOrDefault(key, null);
        if (node == null) {
            return -1;
        }
        // 将 node 从链表中删除
        list.deleteNode(node);
        // 将 node 放在链表的最前端
        list.addNode(node);
        return node.value;
    }

    public void put(int key, int value) {
        // 从 map 中根据 key 获取在链表中的 node
        LinkedListNode node = map.getOrDefault(key, null);
        // 该 node 在链表中存在，修正 node 中 value 的值，同时修正其在链表中的位置
        if (node != null) {
            node.value = value;
            list.deleteNode(node);
            list.addNode(node);
            return;
        }
        // 当前链表已经达到了容量要求，将链表中的最后一个节点删除
        if (map.size() == capacity) {
            int deleteKey = list.deleteTail();
            map.remove(deleteKey);
        }
        // 在链表中创建新的节点，并将其保存在 map 和 list 中
        node = list.addNode(key, value);
        map.put(key, node);
    }

    private static class LinkedListNode {
        public int key;
        public int value;
        public LinkedListNode pre;
        public LinkedListNode next;

        public LinkedListNode(int key, int value, LinkedListNode pre, LinkedListNode next) {
            this.key = key;
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
    }

    private static class LinkedList {
        LinkedListNode virtualHead;
        LinkedListNode virtualTail;

        public LinkedList() {
            virtualHead = new LinkedListNode(-1, -1, null, null);
            virtualTail = new LinkedListNode(-1, -1, null, null);
            virtualHead.next = virtualTail;
            virtualTail.pre = virtualHead;
        }

        public LinkedListNode addNode(int key, int value) {
            LinkedListNode node = new LinkedListNode(key, value, virtualHead, virtualHead.next);
            addNode(node);
            return node;
        }

        public void addNode(LinkedListNode node) {
            node.pre = virtualHead;
            node.next = virtualHead.next;
            virtualHead.next.pre = node;
            virtualHead.next = node;
        }

        public int deleteTail() {
            int result = virtualTail.pre.key;
            deleteNode(virtualTail.pre);
            return result;
        }

        public void deleteNode(LinkedListNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
    }
}
```

