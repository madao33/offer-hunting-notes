#### [剑指OfferII047.二叉树剪枝](https://leetcode-cn.com/problems/pOCWxh/)

难度中等21收藏分享切换为英文接收动态反馈

给定一个二叉树 **根节点** `root` ，树的每个节点的值要么是 `0`，要么是 `1`。请剪除该二叉树中所有节点的值为 `0` 的子树。

节点 `node` 的子树为 `node` 本身，以及所有 `node` 的后代。

 

**示例 1:**

```
输入: [1,null,0,0,1]
输出: [1,null,0,null,1] 
解释: 
只有红色节点满足条件“所有不包含 1 的子树”。
右图为返回的答案。
```

**示例 2:**

```
输入: [1,0,1,0,0,0,1]
输出: [1,null,1,null,1]
解释: 
```

**示例 3:**

```
输入: [1,1,0,1,1,0,1,0]
输出: [1,1,0,1,1,null,1]
解释: 
```

 

**提示:**

- 二叉树的节点个数的范围是 `[1,200]`
- 二叉树节点的值只会是 `0` 或 `1`

## 题解

这道题是二叉树的后序遍历，对于结点值为`0`的叶子结点，直接删除

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
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null)
            return null;
        return root;
    }
}

```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(n)$