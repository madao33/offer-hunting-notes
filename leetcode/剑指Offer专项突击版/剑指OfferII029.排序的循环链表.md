# [剑指OfferII029.排序的循环链表](https://leetcode-cn.com/problems/4ueAj6/)

难度中等23

给定**循环单调非递减列表**中的一个点，写一个函数向这个列表中插入一个新元素 `insertVal` ，使这个列表仍然是循环升序的。

给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。

如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。

如果列表为空（给定的节点是 `null`），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2019/01/19/example_1_before_65p.jpg)
 

```
输入：head = [3,4,1], insertVal = 2
输出：[3,4,1,2]
解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3 。
```

**示例 2：**

```
输入：head = [], insertVal = 1
输出：[1]
解释：列表为空（给定的节点是 null），创建一个循环有序列表并返回这个节点。
```

**示例 3：**

```
输入：head = [1], insertVal = 0
输出：[1,0]
```

 

**提示：**

- `0 <= Number of Nodes <= 5 * 10^4`
- `-10^6 <= Node.val <= 10^6`
- `-10^6 <= insertVal <= 10^6`

## 题解

这道题一看到感觉有很多种情况，但是推理了一下可以分为以下情况

* `head`为空，直接创建结点值为`insertVal`，然后结点的后继指向创建的结点自身，返回创建的结点
* `head`不为空，而链表中的最大值相等或者插入值`insertVal`小于最小值或者大于最大值，这些情况都将结点插入在最大结点之后
* 其他情况就是插入值范围在链表存在值范围内，在链表中查找合适的位置，并插入

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    public Node insert(Node head, int insertVal) {
        // 空节点，创建循环lm
        if (head == null) {
            Node ans = new Node(insertVal);
            ans.next = ans;
            return ans;
        }

        // 找到链表中最大结点
        Node cur = head.next, maxNode = head;
        Node node = new Node(insertVal);
        while(cur != head) {
            if (maxNode.val <= cur.val)
                maxNode = cur;
            cur = cur.next;
        }

        // 如果最大值和最小值相等或者插入值大于尾部结点值或者小于最小值，直接放在最大结点后，也就是最小结点前
        if (maxNode.val == maxNode.next.val || (maxNode.val < insertVal || maxNode.next.val > insertVal)) {
            Node tail = maxNode.next;
            maxNode.next = node;
            node.next = tail;
            return head;
        }

        // 如果插入值不大于链表中最大值也不小于最小值，找到插入位置，并插入值
        cur = head;
        while (cur.val > insertVal || cur.next.val < insertVal) {
            cur = cur.next;
        }
        Node next = cur.next;
        cur.next = node;
        node.next = next;
        return head;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(1)$