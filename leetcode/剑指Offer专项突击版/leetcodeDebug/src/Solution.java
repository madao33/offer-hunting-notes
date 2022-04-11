import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Node pre = null;
            for (int i = queue.size(); i > 0; i--) {
                Node temp = queue.poll();
                if (pre != null) {
                    pre.next = temp;

                }
                temp.next = null;
                pre = temp;
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
        }
        return root;
    }
}