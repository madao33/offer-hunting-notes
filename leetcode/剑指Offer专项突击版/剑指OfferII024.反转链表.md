# [剑指OfferII024.反转链表](https://leetcode-cn.com/problems/UHnkqh/)

难度简单43收藏分享切换为英文接收动态反馈

给定单链表的头节点 `head` ，请反转链表，并返回反转后的链表的头节点。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/02/19/rev1ex1.jpg)

```
输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2021/02/19/rev1ex2.jpg)

```
输入：head = [1,2]
输出：[2,1]
```

**示例 3：**

```
输入：head = []
输出：[]
```

 

**提示：**

- 链表中节点的数目范围是 `[0, 5000]`
- `-5000 <= Node.val <= 5000`



**进阶：**链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？

##  题解

### 迭代法

迭代法翻转链表需要一个辅助指针保存前驱结点的信息

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(1)$

### 递归法

递归法首先可以假想后一段的结点已经翻转，而头结点和后面结点需要的翻转，可以直接`head.next.next = head`，然后设置`head`结点为尾部结点需要设置`head.next = null`放置链表出现环

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(n)$，主要是递归调用的栈空间，最多为 $n$ 层