import java.util.List;

public class mainTest {
    public static void main(String[] args){
        int[] nums = new int[]{1, 3, 3};
        Node head = constructList(nums);

        Node ans = new Solution().insert(head, 4);
        printNode(ans);
    }

    public static Node constructList(int[] nums) {
        Node head = new Node(0);
        Node cur = head;
        for (int num : nums)
        {
            Node temp = new Node(num);
            cur.next = temp;
            cur = temp;
        }
        cur.next = head.next;
        return head.next;
    }

    public static void printNode(Node head) {
        Node cur = head.next;
        System.out.println(head.val);
        while (cur != head) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
