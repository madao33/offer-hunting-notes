# [剑指OfferII084.含有重复元素集合的全排列 ](https://leetcode-cn.com/problems/7p8L0Z/)

难度中等12收藏分享切换为英文接收动态反馈

给定一个可包含重复数字的整数集合 `nums` ，**按任意顺序** 返回它所有不重复的全排列。

 

**示例 1：**

```
输入：nums = [1,1,2]
输出：
[[1,1,2],
 [1,2,1],
 [2,1,1]]
```

**示例 2：**

```
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```

 

**提示：**

- `1 <= nums.length <= 8`
- `-10 <= nums[i] <= 10`

## 题解

和之前的题类似，不过需要注意的是需要设置`visited`数组，如果遇到以下两种情况需要跳过

* 当前数组已经访问过
* 当前数字和之前数字相同

```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        boolean[] visited = new boolean[nums.length];
        dfs(nums, new ArrayList<Integer>(), 0, ans, visited);
        return ans;
    }

    public void dfs(int[] nums, List<Integer> path, int idx, List<List<Integer>> ans, boolean[] visited) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<Integer>(path));
            return ;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])
                continue;
            visited[i] = true;
            path.add(nums[i]);
            dfs(nums, path, idx + 1, ans, visited);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }

}
```

