#### [剑指OfferII061.和最小的k个数对](https://leetcode-cn.com/problems/qn8gGX/)

难度中等19收藏分享切换为英文接收动态反馈

给定两个以升序排列的整数数组 `nums1` 和 `nums2` , 以及一个整数 `k` 。

定义一对值 `(u,v)`，其中第一个元素来自 `nums1`，第二个元素来自 `nums2` 。

请找到和最小的 `k` 个数对 `(u1,v1)`, ` (u2,v2)` ...  `(uk,vk)` 。

 

**示例 1:**

```
输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
输出: [1,2],[1,4],[1,6]
解释: 返回序列中的前 3 对数：
    [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
```

**示例 2:**

```
输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
输出: [1,1],[1,1]
解释: 返回序列中的前 2 对数：
     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
```

**示例 3:**

```
输入: nums1 = [1,2], nums2 = [3], k = 3 
输出: [1,3],[2,3]
解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
```

 

**提示:**

- `1 <= nums1.length, nums2.length <= 104`
- `-109 <= nums1[i], nums2[i] <= 109`
- `nums1`, `nums2` 均为升序排列
- `1 <= k <= 1000`

## 题解

两个数组都是升序排列的，两个数组的和按照升序可能出现的情况是
$$
(a_1, b_1), (a_1+1, b_1), (a_1, b_1+1),\cdots,(a_n+1, b_n),(a_n, b_n+1)
$$
可以首先将`nums1`的前`k`加入到队伍中，每次从队伍中去除元素`(x, y)`，我们只需要将`nums2`的索引增加即可

```java
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (index1, index2) -> {
            return nums1[index1[0]] + nums2[index1[1]] - nums1[index2[0]] - nums2[index2[1]]; 
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length, n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[] {i, 0});
        }

        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }
}
```

