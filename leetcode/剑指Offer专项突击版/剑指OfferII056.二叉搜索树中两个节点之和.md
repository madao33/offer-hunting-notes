# [剑指OfferII056.二叉搜索树中两个节点之和](https://leetcode-cn.com/problems/opLdQZ/)

难度简单14收藏分享切换为英文接收动态反馈

给定一个二叉搜索树的 **根节点** `root` 和一个整数 `k` , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 `k` 。假设二叉搜索树中节点的值均唯一。

 

**示例 1：**

```
输入: root = [8,6,10,5,7,9,11], k = 12
输出: true
解释: 节点 5 和节点 7 之和等于 12
```

**示例 2：**

```
输入: root = [8,6,10,5,7,9,11], k = 22
输出: false
解释: 不存在两个节点值之和为 22 的节点
```

 

**提示：**

- 二叉树的节点个数的范围是 `[1, 104]`.
- `-104 <= Node.val <= 104`
- `root` 为二叉搜索树
- `-105 <= k <= 105`

## 题解

这道题的要求在于：**查找二叉排序树中是否存在两个节点的值之和等于`k`**，和之前的有序数组中查找两个数之和等于`target`类似，可以实现通过中序遍历获取二叉排序树的序列化形式，也就是将其转换为有序数组。

然后查找任意两数之和等于目标数，就可以直接通过双指针实现。

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
    public boolean findTarget(TreeNode root, int k) {
        ArrayList<Integer> arr = new ArrayList<>();
        inOrder(root, arr);
        int left = 0, right = arr.size() - 1;
        while(left < right) {
            if (arr.get(left) + arr.get(right) < k)
                left++;
            else if (arr.get(left) + arr.get(right) > k)
                right--;
            else if (arr.get(left) + arr.get(right) == k)
                return true;
        }
        return false;
    }

    public void inOrder(TreeNode root, ArrayList<Integer> arr) {
        if (root == null) 
            return;
        inOrder(root.left, arr);
        arr.add(root.val);
        inOrder(root.right, arr);
    }
}
```

* 时间复杂度：$O(N)$
* 空间复杂度：$O(N)$
