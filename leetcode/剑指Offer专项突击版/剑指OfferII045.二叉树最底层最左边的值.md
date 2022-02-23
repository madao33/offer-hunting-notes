#### [剑指OfferII045.二叉树最底层最左边的值](https://leetcode-cn.com/problems/LwUNpT/)

难度中等13

给定一个二叉树的 **根节点** `root`，请找出该二叉树的 **最底层 最左边** 节点的值。

假设二叉树中至少有一个节点。

 

**示例 1:**

![img](https://assets.leetcode.com/uploads/2020/12/14/tree1.jpg)

```
输入: root = [2,1,3]
输出: 1
```

**示例 2:**

![img](https://assets.leetcode.com/uploads/2020/12/14/tree2.jpg)

```
输入: [1,2,3,4,null,5,6,null,null,7]
输出: 7
```

 

**提示:**

- 二叉树的节点个数的范围是 `[1,104]`
- `-231 <= Node.val <= 231 - 1` 

## 题解

这道题需要找到二叉树最后一层的最左边的结点，可以直接使用二叉树的层序遍历，但是左右结点遍历顺序调换一下，先遍历右结点，再遍历左结点，然后返回最后一个节点的值

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        int ans = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                TreeNode temp = queue.poll();
                ans = temp.val;
                if (temp.right != null) queue.offer(temp.right);
                if (temp.left != null) queue.offer(temp.left);
            }
        }
        return ans;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(1)$