#### [剑指OfferII044.二叉树每层的最大值](https://leetcode-cn.com/problems/hPov7L/)

难度中等12收藏分享切换为英文接收动态反馈

给定一棵二叉树的根节点 `root` ，请找出该二叉树中每一层的最大值。

 

**示例1：**

```
输入: root = [1,3,2,5,3,null,9]
输出: [1,3,9]
解释:
          1
         / \
        3   2
       / \   \  
      5   3   9 
```

**示例2：**

```
输入: root = [1,2,3]
输出: [1,3]
解释:
          1
         / \
        2   3
```

**示例3：**

```
输入: root = [1]
输出: [1]
```

**示例4：**

```
输入: root = [1,null,2]
输出: [1,2]
解释:      
           1 
            \
             2     
```

**示例5：**

```
输入: root = []
输出: []
```

 

**提示：**

- 二叉树的节点个数的范围是 `[0,104]`
- `-231 <= Node.val <= 231 - 1`

## 题解

这道题是二叉树的层序遍历的简单变种，使用队列保存需要遍历的二叉树，对于每一层的结点，找出一个最大值

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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return ans;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int max = queue.peek().val;
            for (int i = queue.size(); i > 0; i--) {
                TreeNode temp = queue.poll();
                max = Math.max(max, temp.val);
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
            ans.add(max);
        }
        return ans;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(n)$