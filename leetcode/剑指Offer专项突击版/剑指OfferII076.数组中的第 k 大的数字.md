#### [剑指OfferII076.数组中的第 k 大的数字](https://leetcode-cn.com/problems/xx4gT2/)

难度中等25收藏分享切换为英文接收动态反馈

给定整数数组 `nums` 和整数 `k`，请返回数组中第 `**k**` 个最大的元素。

请注意，你需要找的是数组排序后的第 `k` 个最大的元素，而不是第 `k` 个不同的元素。

 

**示例 1:**

```
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
```

**示例 2:**

```
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
```

 

**提示：**

- `1 <= k <= nums.length <= 104`
- `-104 <= nums[i] <= 104`

##  题解

### 直接排序然后查找

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0, len = nums.length-1;
        for(int i = len; i > len-k; i--)
            ans = nums[i];
        return ans;
    }
}
```

### 堆排序

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num : nums) {
            pq.offer(num);
            if(pq.size() > k) pq.poll();
        }
        return pq.peek();
    }
}
```

