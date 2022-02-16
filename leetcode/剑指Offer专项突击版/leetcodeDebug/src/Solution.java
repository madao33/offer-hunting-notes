

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
        while (cur.val >= insertVal || cur.next.val < insertVal) {
            cur = cur.next;
        }
        Node next = cur.next;
        cur.next = node;
        node.next = next;
        return head;
    }
}