# [剑指OfferII026.重排链表](https://leetcode-cn.com/problems/LGjMqU/)

难度中等31收藏分享切换为英文接收动态反馈

给定一个单链表 `L` 的头节点 `head` ，单链表 `L` 表示为：

` L0 → L1 → … → Ln-1 → Ln `
请将其重新排列后变为：

```
L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
```

不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 

**示例 1:**

![img](https://pic.leetcode-cn.com/1626420311-PkUiGI-image.png)

```
输入: head = [1,2,3,4]
输出: [1,4,2,3]
```

**示例 2:**

![img](https://pic.leetcode-cn.com/1626420320-YUiulT-image.png)

```
输入: head = [1,2,3,4,5]
输出: [1,5,2,4,3]
```

 

**提示：**

- 链表的长度范围为 `[1, 5 * 104]`
- `1 <= node.val <= 1000`

## 题解

这道题是一个符合操作的链表题，可以看到题目的要求是将链表后半部分反转合并到链表的前半部分，所以实现的流程可以是：

* 找到链表的中间结点`middleNode`
* 反转后半部分`reverseList`
* 合并链表`mergeList`

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
    public void reorderList(ListNode head) {
        if (head == null) return;

        ListNode mid = middleNode(head);
        // l2只取后半部分，不包含中间结点
        ListNode l1 = head, l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode nextTemp = cur.next;
            cur.next = prev; 
            prev = cur;
            cur = nextTemp;
        }
        return prev;
    }

    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1temp, l2temp;
        while(l1 != null && l2 != null) {
            l1temp = l1.next;
            l2temp = l2.next;

            l1.next = l2;
            l1 = l1temp;

            l2.next = l1;
            l2 = l2temp;
        }
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(n)$