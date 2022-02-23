# [剑指OfferII041.滑动窗口的平均值](https://leetcode-cn.com/problems/qIsx9U/)

难度简单12收藏分享切换为英文接收动态反馈

给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。

实现 `MovingAverage` 类：

- `MovingAverage(int size)` 用窗口大小 `size` 初始化对象。
- `double next(int val)` 成员函数 `next` 每次调用的时候都会往滑动窗口增加一个整数，请计算并返回数据流中最后 `size` 个值的移动平均值，即滑动窗口里所有数字的平均值。

 

**示例：**

```
输入：
inputs = ["MovingAverage", "next", "next", "next", "next"]
inputs = [[3], [1], [10], [3], [5]]
输出：
[null, 1.0, 5.5, 4.66667, 6.0]

解释：
MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // 返回 1.0 = 1 / 1
movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
```

 

**提示：**

- `1 <= size <= 1000`
- `-105 <= val <= 105`
- 最多调用 `next` 方法 `104` 次

## 题解

直接使用`Deque`，关于双端队列有以下相关API

|                    |                       **`Queue` 方法**                       |                    **等效 `Deque` 方法**                     |
| :----------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|  插入（抛出异常）  | [`add(e)`](https://blog.csdn.net/top_code/article/details/8650729?ops_request_misc=&request_id=&biz_id=102&utm_term=java Deque遍历&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduweb~default-7-8650729.nonecase&spm=1018.2226.3001.4450) | [`addLast(e)`](https://blog.csdn.net/top_code/article/details/8650729?ops_request_misc=&request_id=&biz_id=102&utm_term=java Deque遍历&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduweb~default-7-8650729.nonecase&spm=1018.2226.3001.4450) |
| 插入（抛出特殊值） | [`offer(e)`](https://blog.csdn.net/top_code/article/details/8650729?ops_request_misc=&request_id=&biz_id=102&utm_term=java Deque遍历&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduweb~default-7-8650729.nonecase&spm=1018.2226.3001.4450) | [`offerLast(e)`](https://blog.csdn.net/top_code/article/details/8650729?ops_request_misc=&request_id=&biz_id=102&utm_term=java Deque遍历&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduweb~default-7-8650729.nonecase&spm=1018.2226.3001.4450) |
|  移除（抛出异常）  | [`remove()`](https://blog.csdn.net/top_code/article/details/8650729?ops_request_misc=&request_id=&biz_id=102&utm_term=java Deque遍历&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduweb~default-7-8650729.nonecase&spm=1018.2226.3001.4450) | [`removeFirst()`](https://blog.csdn.net/top_code/article/details/8650729?ops_request_misc=&request_id=&biz_id=102&utm_term=java Deque遍历&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduweb~default-7-8650729.nonecase&spm=1018.2226.3001.4450) |
| 移除（抛出特殊值） | [`poll()`](https://blog.csdn.net/top_code/article/details/8650729?ops_request_misc=&request_id=&biz_id=102&utm_term=java Deque遍历&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduweb~default-7-8650729.nonecase&spm=1018.2226.3001.4450) | [`pollFirst()`](https://blog.csdn.net/top_code/article/details/8650729?ops_request_misc=&request_id=&biz_id=102&utm_term=java Deque遍历&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduweb~default-7-8650729.nonecase&spm=1018.2226.3001.4450) |
|  检查（抛出异常）  | [`element()`](https://blog.csdn.net/top_code/article/details/8650729?ops_request_misc=&request_id=&biz_id=102&utm_term=java Deque遍历&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduweb~default-7-8650729.nonecase&spm=1018.2226.3001.4450) | [`getFirst()`](https://blog.csdn.net/top_code/article/details/8650729?ops_request_misc=&request_id=&biz_id=102&utm_term=java Deque遍历&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduweb~default-7-8650729.nonecase&spm=1018.2226.3001.4450) |
| 检查（抛出特殊值） | [`peek()`](https://blog.csdn.net/top_code/article/details/8650729?ops_request_misc=&request_id=&biz_id=102&utm_term=java Deque遍历&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduweb~default-7-8650729.nonecase&spm=1018.2226.3001.4450) | [`peekFirst()`](https://blog.csdn.net/top_code/article/details/8650729?ops_request_misc=&request_id=&biz_id=102&utm_term=java Deque遍历&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduweb~default-7-8650729.nonecase&spm=1018.2226.3001.4450) |

检测队列的大小

```java
queue.size();
```

遍历队列，可以使用`for-each`语法

```java
for (Integer val : queue)
    System.out.println(val);
```

本题的相关题解如下代码所示：

```java
class MovingAverage {
    Deque<Double> queue;
    int Maxmum = 0;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        queue = new LinkedList<>();
        Maxmum = size;
    }
    
    public double next(int val) {
        while (queue.size() >= Maxmum) queue.pollFirst();
        queue.addLast((double) val);
        double avg = 0.0;
        for (Double v : queue) avg += v;
        return avg / queue.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
```

