# [剑指OfferII079.所有子集](https://leetcode-cn.com/problems/TVdhkn/)

难度中等19收藏分享切换为英文接收动态反馈

给定一个整数数组 `nums` ，数组中的元素 **互不相同** 。返回该数组所有可能的子集（幂集）。

解集 **不能** 包含重复的子集。你可以按 **任意顺序** 返回解集。

 

**示例 1：**

```
输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
```

**示例 2：**

```
输入：nums = [0]
输出：[[],[0]]
```

 

**提示：**

- `1 <= nums.length <= 10`
- `-10 <= nums[i] <= 10`
- `nums` 中的所有元素 **互不相同**

## 题解

对于数组中的每个元素可以考虑加入子集和不加入子集两种情况，使用递归实现，需要注意递归退出的回溯

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0, new ArrayList<>());
        return ans;
    }

    private void dfs(int[] nums, int idx, List<Integer> temp) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(temp));
            return;
        }

        dfs(nums, idx+1, temp);

        temp.add(nums[idx]);
        dfs(nums, idx+1, temp);
        temp.remove(temp.size() - 1);
    }
}
```

