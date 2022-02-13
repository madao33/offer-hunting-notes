#### [剑指OfferII021.删除链表的倒数第n个结点](https://leetcode-cn.com/problems/SLwz0R/)

难度中等

给定一个链表，删除链表的倒数第 `n` 个结点，并且返回链表的头结点。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/10/03/remove_ex1.jpg)

```
输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]
```

**示例 2：**

```
输入：head = [1], n = 1
输出：[]
```

**示例 3：**

```
输入：head = [1,2], n = 1
输出：[1]
```

 

**提示：**

- 链表中结点的数目为 `sz`
- `1 <= sz <= 30`
- `0 <= Node.val <= 100`
- `1 <= n <= sz`

 

**进阶：**能尝试使用一趟扫描实现吗？

##  题解

首先计算链表长度，然后`next`到倒数第`n`个结点的前驱结点，执行删除第`n`个结点，返回头结点

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        // 添加一个空结点方便操作
        ListNode dump = new ListNode(0, head), p = dump;
        
        // 计算链表长度
        int length = 0;
        while(p != null) {
            ++length;
            p = p.next;
        }
        
        // 到倒数第n个结点的前驱结点
        p = dump;
        for(int i = 0; i < length - n -1; i++, p = p.next);
        
        // 删除倒数第n个结点
        p.next = p.next.next;
        // 返回头结点
        return dump.next;
    }
}
```

