# [剑指OfferII028.展平多级双向链表](https://leetcode-cn.com/problems/Qv1Da2/)

难度中等17收藏分享切换为英文接收动态反馈

多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。

给定位于列表第一级的头节点，请扁平化列表，即将这样的多级双向链表展平成普通的双向链表，使所有结点出现在单级双链表中。

 

**示例 1：**

```
输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
输出：[1,2,3,7,8,11,12,9,10,4,5,6]
解释：

输入的多级列表如下图所示：



扁平化后的链表如下图：
```

**示例 2：**

```
输入：head = [1,2,null,3]
输出：[1,3,2]
解释：

输入的多级列表如下图所示：

  1---2---NULL
  |
  3---NULL
```

**示例 3：**

```
输入：head = []
输出：[]
```

 

**如何表示测试用例中的多级链表？**

以 **示例 1** 为例：

```
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
```

序列化其中的每一级之后：

```
[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
```

为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。

```
[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]
```

合并所有序列化结果，并去除末尾的 null 。

```
[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
```

 

**提示：**

- 节点数目不超过 `1000`
- `1 <= Node.val <= 10^5`

## 题解

主要还是DFS算法掌握的不是很熟练

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        DFS(head);
        return head;
    }

    public Node DFS(Node node) {
        Node cur = node;
        Node last = null;

        while (cur != null) {
            Node next = cur.next;
            if (cur.child != null) {
                Node childLast = DFS(cur.child);

                next = cur.next;

                cur.next = cur.child;
                cur.child.prev = cur;

                if (next != null) {
                    childLast.next = next;
                    next.prev = childLast;
                }

                cur.child = null;
                last = childLast;
            }
            else {
                last = cur;
            }
            cur = next;
        }
        return last;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(n)$