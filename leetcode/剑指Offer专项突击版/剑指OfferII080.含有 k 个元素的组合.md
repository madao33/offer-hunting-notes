# [剑指OfferII080.含有 k 个元素的组合](https://leetcode-cn.com/problems/uUsW3B/)

难度中等14收藏分享切换为英文接收动态反馈

给定两个整数 `n` 和 `k`，返回 `1 ... n` 中所有可能的 `k` 个数的组合。

 

**示例 1:**

```
输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```

**示例 2:**

```
输入: n = 1, k = 1
输出: [[1]]
```

 

**提示:**

- `1 <= n <= 20`
- `1 <= k <= n`

## 题解

利用DFS实现K个元素的组合，需要注意去除剪枝，减少不必要的运算量

```java
class Solution {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(n, 1, k, new ArrayList<>());
        return ans;
    }

    public void dfs(int n, int cur, int k, List<Integer> list) {
        if (n - cur + 1 < k) return ;
        if (k == 0) {
            ans.add(new ArrayList<>(list));
            return ;
        }

        list.add(cur);
        dfs(n, cur + 1, k - 1, list);
        list.remove(list.size() - 1);
        dfs(n, cur + 1, k, list);
    }
}
```

