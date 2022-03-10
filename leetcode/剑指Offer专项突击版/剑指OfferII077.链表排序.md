#### [剑指OfferII077.链表排序](https://leetcode-cn.com/problems/7WHec2/)

难度中等35收藏分享切换为英文接收动态反馈

给定链表的头结点 `head` ，请将其按 **升序** 排列并返回 **排序后的链表** 。



 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/09/14/sort_list_1.jpg)

```
输入：head = [4,2,1,3]
输出：[1,2,3,4]
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2020/09/14/sort_list_2.jpg)

```
输入：head = [-1,5,3,4,0]
输出：[-1,0,3,4,5]
```

**示例 3：**

```
输入：head = []
输出：[]
```

 

**提示：**

- 链表中节点的数目在范围 `[0, 5 * 104]` 内
- `-105 <= Node.val <= 105`

## 题解

使用优先队列实现结点排序，然后在从小到大出队并以插入的方式重新构建链表

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
    public ListNode sortList(ListNode head) {
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            public int compare(ListNode node1, ListNode node2) {
                return node1.val - node2.val;
            }
        });

        while (head != null) {
            pq.offer(head);
            head = head.next;
        }

        ListNode ans = new ListNode();
        ListNode cur = ans;
        while(pq.size() > 0) {
            cur.next = pq.peek();
            pq.poll();
            cur = cur.next;
        }
        cur.next = null;
        return ans.next;
    }
}
```

* 时间复杂度：$(Nlog(N))$
* 空间复杂度：$O(N)$