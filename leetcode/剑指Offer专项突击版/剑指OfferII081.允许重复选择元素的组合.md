# [剑指OfferII081.允许重复选择元素的组合](https://leetcode-cn.com/problems/Ygoe9J/)

难度中等15收藏分享切换为英文接收动态反馈

给定一个**无重复元素**的正整数数组 `candidates` 和一个正整数 `target` ，找出 `candidates` 中所有可以使数字和为目标数 `target` 的唯一组合。

`candidates` 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是不同的。 

对于给定的输入，保证和为 `target` 的唯一组合数少于 `150` 个。

 

**示例 1：**

```
输入: candidates = [2,3,6,7], target = 7
输出: [[7],[2,2,3]]
```

**示例 2：**

```
输入: candidates = [2,3,5], target = 8
输出: [[2,2,2,2],[2,3,3],[3,5]]
```

**示例 3：**

```
输入: candidates = [2], target = 1
输出: []
```

**示例 4：**

```
输入: candidates = [1], target = 1
输出: [[1]]
```

**示例 5：**

```
输入: candidates = [1], target = 2
输出: [[1,1]]
```

 

**提示：**

- `1 <= candidates.length <= 30`
- `1 <= candidates[i] <= 200`
- `candidate` 中的每个元素都是独一无二的。
- `1 <= target <= 500`

## 题解

基本的思想和[剑指OfferII080.含有 k 个元素的组合](剑指OfferII080.含有 k 个元素的组合.md)类似，不过需要注意的是实现可重复选取元素，并且保证添加的元素之和为`targer`，添加之前计算当前列表元素之和是否不超过`target`，不超过才能添加，并且添加之后索引不自增

```java
class Solution {
    
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, 0, target, new ArrayList<Integer>());
        return ans;
    }

    public void dfs(int[] candidates, int cur, int target, List<Integer> combine) {
        if (cur == candidates.length) return ;

        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return ;
        }

        dfs(candidates, cur + 1, target, combine);

        if (target - candidates[cur] >= 0) {
            combine.add(candidates[cur]);
            dfs(candidates, cur, target - candidates[cur], combine);
            combine.remove(combine.size() - 1);
        }
    }
}
```

