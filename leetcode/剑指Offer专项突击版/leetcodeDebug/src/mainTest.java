import java.util.List;

public class mainTest {
    public static void main(String[] args){
        Node root = new Node(3);
        Node t2 = new Node(2);
        root.left = t2;
        Node t3 = new Node(3);
        root.right = t3;
        Node t4 = new Node(4);
        t2.left = t4;
        Node t5 = new Node(5);
        t2.right = t5;
        Node t7 = new Node(7);
        t3.right = t7;


        Node res = new Solution().connect(root);

        System.out.println(res.val);

    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
