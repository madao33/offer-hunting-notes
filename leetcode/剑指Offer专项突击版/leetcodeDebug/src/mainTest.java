import java.util.List;

public class mainTest {
    public static void main(String[] args){
        int[] nums = new int[]{1, 0, 0};
        ListNode head = constructLinkedList(nums);
        boolean res = new Solution().isPalindrome(head);
        System.out.println(res);

    }

    public static ListNode constructLinkedList(int[] nums) {
        ListNode head = new ListNode();
        ListNode cur = head;
        for (int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
            cur.next = null;
        }
        return head.next;
    }
}
