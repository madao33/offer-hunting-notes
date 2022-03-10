# [剑指OfferII082.含有重复元素集合的组合](https://leetcode-cn.com/problems/4sjJUc/)

难度中等16收藏分享切换为英文接收动态反馈

给定一个可能有重复数字的整数数组 `candidates` 和一个目标数 `target` ，找出 `candidates` 中所有可以使数字和为 `target` 的组合。

`candidates` 中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合。 

 

**示例 1:**

```
输入: candidates = [10,1,2,7,6,1,5], target = 8,
输出:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
```

**示例 2:**

```
输入: candidates = [2,5,2,1,2], target = 5,
输出:
[
[1,2,2],
[5]
]
```

 

**提示:**

- `1 <= candidates.length <= 100`
- `1 <= candidates[i] <= 50`
- `1 <= target <= 30`

## 题解

因为元素中含有重复的元素，为了避免重复数字，可以事先对候选数组进行排序，然后在添加组合的时候可以判断之前的元素和当前元素是否相同，如果相同就跳过当前元素，否则就继续进行组合

```java
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int n = candidates.length;
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        dfs(candidates, n, 0, target, new ArrayList<>(), ans);
        return ans;
    }

    public void dfs(int[] candidates, int n, int idx, int target, List<Integer> combine, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return ;
        }

        for (int i = idx; i < n; i++) {
            if (candidates[i] > target)
                break;
            if (i > idx && candidates[i] == candidates[i - 1])
                continue;
            combine.add(candidates[i]);
            dfs(candidates, n, i + 1, target - candidates[i], combine, ans);
            combine.remove(combine.size() - 1);
        }
    }
}
```

