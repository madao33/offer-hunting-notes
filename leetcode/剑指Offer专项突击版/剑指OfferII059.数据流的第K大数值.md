# [剑指OfferII059.数据流的第K大数值](https://leetcode-cn.com/problems/jBjn9C/)

难度简单17收藏分享切换为英文接收动态反馈

设计一个找到数据流中第 `k` 大元素的类（class）。注意是排序后的第 `k` 大元素，不是第 `k` 个不同的元素。

请实现 `KthLargest` 类：

- `KthLargest(int k, int[] nums)` 使用整数 `k` 和整数流 `nums` 初始化对象。
- `int add(int val)` 将 `val` 插入数据流 `nums` 后，返回当前数据流中第 `k` 大的元素。

 

**示例：**

```
输入：
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
输出：
[null, 4, 5, 5, 8, 8]

解释：
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3);   // return 4
kthLargest.add(5);   // return 5
kthLargest.add(10);  // return 5
kthLargest.add(9);   // return 8
kthLargest.add(4);   // return 8
```

 

**提示：**

- `1 <= k <= 104`
- `0 <= nums.length <= 104`
- `-104 <= nums[i] <= 104`
- `-104 <= val <= 104`
- 最多调用 `add` 方法 `104` 次
- 题目数据保证，在查找第 `k` 大元素时，数组中至少有 `k` 个元素

## 题解

使用一个大小为`k`优先队列，队列中仅保存`k`个元素，如果超过，队头出队

对于优先队列`PriorityQueue`

* `peek()` 返回队首元素
* `poll()` 队首元素出队，并返回队首元素
* `add()` 添加元素
* `size()` 返回队伍中元素个数
* `isEmpty()` 判断队伍是否为空，为空返回`true`，不空返回`false`

```java
class KthLargest {
    PriorityQueue<Integer> pqueue;
    int k;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        pqueue = new PriorityQueue<>();
        for (int num : nums) {
            add(num);
        }
    }
    
    public int add(int val) {
        pqueue.offer(val);
        if(pqueue.size() > k)
            pqueue.poll();
        return pqueue.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
```

