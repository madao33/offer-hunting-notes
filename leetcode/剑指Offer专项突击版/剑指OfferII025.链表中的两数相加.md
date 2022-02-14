# [剑指OfferII025.链表中的两数相加](https://leetcode-cn.com/problems/lMSNwu/)

难度中等25收藏分享切换为英文接收动态反馈

给定两个 **非空链表** `l1`和 `l2` 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。

可以假设除了数字 0 之外，这两个数字都不会以零开头。

 

**示例1：**

![img](https://pic.leetcode-cn.com/1626420025-fZfzMX-image.png)

```
输入：l1 = [7,2,4,3], l2 = [5,6,4]
输出：[7,8,0,7]
```

**示例2：**

```
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[8,0,7]
```

**示例3：**

```
输入：l1 = [0], l2 = [0]
输出：[0]
```

 

**提示：**

- 链表的长度范围为` [1, 100]`
- `0 <= node.val <= 9`
- 输入数据保证链表代表的数字无前导 0

 

**进阶：**如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。

## 题解

一个简单的思想是通过两个栈将两个链表的值全部拷贝至栈中，然后分别弹栈而出进行10进制加法，按照竖列式加法的方式处理进位，注意最后的进位存在也需要创建一个节点

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>(), stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        ListNode ans = null;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int sum = 0;
            if (!stack1.isEmpty())
                sum += stack1.pop();
            if (!stack2.isEmpty())
                sum += stack2.pop();
            sum += carry;
            carry = sum / 10;
            sum %= 10;
            ListNode node = new ListNode(sum, ans);
            ans = node;
        }
        if (carry != 0) {
            ListNode node = new ListNode(carry, ans);
            ans = node;
        }
        return ans;
    }
}
```

* 时间复杂度：$O(n+m)$
* 空间复杂度：$O(n+m)$