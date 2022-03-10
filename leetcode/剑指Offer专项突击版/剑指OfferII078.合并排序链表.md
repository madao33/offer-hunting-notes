#### [剑指OfferII078.合并排序链表](https://leetcode-cn.com/problems/vvXgSW/)

难度困难26收藏分享切换为英文接收动态反馈

给定一个链表数组，每个链表都已经按升序排列。

请将所有链表合并到一个升序链表中，返回合并后的链表。

 

**示例 1：**

```
输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6
```

**示例 2：**

```
输入：lists = []
输出：[]
```

**示例 3：**

```
输入：lists = [[]]
输出：[]
```

 

**提示：**

- `k == lists.length`
- `0 <= k <= 10^4`
- `0 <= lists[i].length <= 500`
- `-10^4 <= lists[i][j] <= 10^4`
- `lists[i]` 按 **升序** 排列
- `lists[i].length` 的总和不超过 `10^4`

## 题解

这道题可以看作是之前合并两个链表的升级版，为了避免直接遍历合并导致的大链表遍历的效率问题，可以结合归并排序的思想，先两两之间合并，然后再合并到一起

```
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
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0)
            return null;
        return mergeList(lists, 0, len - 1);
    }

    public ListNode mergeList(ListNode[] lists, int left, int right) {
        if (left == right)
            return lists[left];
        int mid = (left + right) / 2;
        ListNode L = mergeList(lists, left, mid);
        ListNode R = mergeList(lists, mid+1, right);
        return merge(L, R);
    }

    public ListNode merge(ListNode root1, ListNode root2) {
        ListNode head = new ListNode();
        ListNode cur = head;

        while(root1 != null && root2 != null) {
            if (root1.val < root2.val) {
                cur.next = root1;
                root1 = root1.next;
            } else {
                cur.next = root2;
                root2 = root2.next;
            }
            cur = cur.next;
        }
        cur.next = root1 == null ? root2 : root1;

        return head.next;
    }
}
```

