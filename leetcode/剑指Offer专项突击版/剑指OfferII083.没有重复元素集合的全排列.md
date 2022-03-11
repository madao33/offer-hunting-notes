# [剑指OfferII083.没有重复元素集合的全排列](https://leetcode-cn.com/problems/VvJkup/)

难度中等14收藏分享切换为英文接收动态反馈

给定一个不含重复数字的整数数组 `nums` ，返回其 **所有可能的全排列** 。可以 **按任意顺序** 返回答案。

 

**示例 1：**

```
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```

**示例 2：**

```
输入：nums = [0,1]
输出：[[0,1],[1,0]]
```

**示例 3：**

```
输入：nums = [1]
输出：[[1]]
```

 

**提示：**

- `1 <= nums.length <= 6`
- `-10 <= nums[i] <= 10`
- `nums` 中的所有整数 **互不相同**

## 题解

全排列的过程中需要注意去重

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        dfs(nums, new ArrayList<Integer>(), ans);
        return ans;
    }

    public void dfs(int[] nums, List<Integer> combine, List<List<Integer>> ans) {
        if (combine.size() == nums.length) {
            ans.add(new ArrayList<Integer>(combine));
            return ;
        }

        for (int i = 0; i < nums.length; i++) {
            if (combine.contains(nums[i]))
                continue;
                combine.add(nums[i]);
                dfs(nums, combine, ans);
                combine.remove(combine.size() - 1);
            
        }
    }
}
```

