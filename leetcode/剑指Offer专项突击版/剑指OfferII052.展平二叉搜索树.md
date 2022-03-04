# [剑指OfferII052.展平二叉搜索树](https://leetcode-cn.com/problems/NYBBNL/)

难度简单

给你一棵二叉搜索树，请 **按中序遍历** 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/11/17/ex1.jpg)

```
输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2020/11/17/ex2.jpg)

```
输入：root = [5,1,7]
输出：[1,null,5,null,7]
```

 

**提示：**

- 树中节点数的取值范围是 `[1, 100]`
- `0 <= Node.val <= 1000`

## 题解

二叉树的中序遍历

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
    public TreeNode increasingBST(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        inOrder(root, res);

        TreeNode ans = new TreeNode(0);
        TreeNode p = ans;
        for (int val : res) {
            TreeNode temp = new TreeNode(val);
            p.right = temp;
            p = p.right;
        }
        return ans.right;
    }

    public void inOrder(TreeNode root, ArrayList res) {
        if (root == null) {
            return ;
        }
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }
}
```

* 时间复杂度：$O(N)$
* 空间复杂度：$O(N)$