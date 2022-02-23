# [剑指OfferII043.往完全二叉树添加节点](https://leetcode-cn.com/problems/NaqhDT/)

难度中等11收藏分享切换为英文接收动态反馈

完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大，第 `n` 层有 `2n-1` 个节点）的，并且所有的节点都尽可能地集中在左侧。

设计一个用完全二叉树初始化的数据结构 `CBTInserter`，它支持以下几种操作：

- `CBTInserter(TreeNode root)` 使用根节点为 `root` 的给定树初始化该数据结构；
- `CBTInserter.insert(int v)` 向树中插入一个新节点，节点类型为 `TreeNode`，值为 `v` 。使树保持完全二叉树的状态，**并返回插入的新节点的父节点的值**；
- `CBTInserter.get_root()` 将返回树的根节点。

 



**示例 1：**

```
输入：inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
输出：[null,1,[1,2]]
```

**示例 2：**

```
输入：inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
输出：[null,3,4,[1,2,3,4,5,6,7,8]]
```

 

**提示：**

- 最初给定的树是完全二叉树，且包含 `1` 到 `1000` 个节点。
- 每个测试用例最多调用 `CBTInserter.insert` 操作 `10000` 次。
- 给定节点或插入节点的每个值都在 `0` 到 `5000` 之间。

## 题解

使用一个队列遍历完全二叉树，如果左右结点都不为空，将左右结点添加到队列中，如果为空，则中断遍历，这时队列中的头结点是第一个不满的结点，判断其左右是否为空，然后进行插入

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
class CBTInserter {
    
    Queue<TreeNode> queue;
    TreeNode root;
    public CBTInserter(TreeNode root) {
        this.root = root;
        queue = new LinkedList<>();
        queue.offer(root);
    }
    
    public int insert(int v) {
        TreeNode insertNode = new TreeNode(v);
        while(queue.peek().left != null && queue.peek().right != null) {
            TreeNode temp = queue.poll();
            queue.offer(temp.left);
            queue.offer(temp.right);   
        }
        TreeNode temp = queue.peek();
        if(temp.left == null) {
            temp.left = insertNode;
            return temp.val;
        }
        temp.right = insertNode;
        return temp.val;
    }
    
    public TreeNode get_root() {
        return root;
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(n)$

