# [剑指OfferII027.回文链表](https://leetcode-cn.com/problems/aMhZSa/)

难度简单26收藏分享切换为英文接收动态反馈

给定一个链表的 **头节点** `head` **，**请判断其是否为回文链表。

如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。

 

**示例 1：**

**![img](https://pic.leetcode-cn.com/1626421737-LjXceN-image.png)**

```
输入: head = [1,2,3,3,2,1]
输出: true
```

**示例 2：**

**![img](https://pic.leetcode-cn.com/1626422231-wgvnWh-image.png)**

```
输入: head = [1,2]
输出: false
```

 

**提示：**

- 链表 L 的长度范围为 `[1, 105]`
- `0 <= node.val <= 9`

 

**进阶：**能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

## 题解

这一题的思路同[剑指OfferII026.重排链表](剑指OfferII026.重排链表.md)基本思想一致，首先还是获取链表的中间结点，然后将后半部分的链表反转，最后判断前半部分链表和后半反转链表是否相等，判断的时候的退出条件需要注意是`head !=null && head2 != null`，也就是两个链表都没有遍历完的时候判断，因为两个链表长度相差为1，只会有两种情况：

* 一种是两个链表长度相同，同时遍历结束
* 一种是前半链表多一个节点，如果相同长度的结点值都相同，也是回文链表

所以只需要判断两个链表的公共结点的值是否相同就可以了

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
    public boolean isPalindrome(ListNode head) {
        ListNode mid = middleNode(head);
        ListNode head2 = mid.next;
        mid.next = null;
        head2 = reverseList(head2);
        return equalList(head, head2);
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode pre = null, cur = head;
        while(cur != null) {
            ListNode nextNode = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextNode;
        }
        return pre;
    }

    public boolean equalList(ListNode head, ListNode head2) {
        while(head != null && head2 != null) {
            if(head.val != head2.val) return false;
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(1)$