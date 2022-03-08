# [剑指OfferII069.山峰数组的顶部](https://leetcode-cn.com/problems/B1IidL/)

难度简单74收藏分享切换为英文接收动态反馈

符合下列属性的数组 `arr` 称为 **山峰数组**（**山脉数组）** ：

- `arr.length >= 3`

- 存在

   

  ```
  i
  ```

  （

  ```
  0 < i < arr.length - 1
  ```

  ）使得：

  - `arr[0] < arr[1] < ... arr[i-1] < arr[i]`
  - `arr[i] > arr[i+1] > ... > arr[arr.length - 1]`

给定由整数组成的山峰数组 `arr` ，返回任何满足 `arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1]` 的下标 `i` ，即山峰顶部。

 

**示例 1：**

```
输入：arr = [0,1,0]
输出：1
```

**示例 2：**

```
输入：arr = [1,3,5,4,2]
输出：2
```

**示例 3：**

```
输入：arr = [0,10,5,2]
输出：1
```

**示例 4：**

```
输入：arr = [3,4,5,1]
输出：2
```

**示例 5：**

```
输入：arr = [24,69,100,99,79,78,67,36,26,19]
输出：2
```

 

**提示：**

- `3 <= arr.length <= 104`
- `0 <= arr[i] <= 106`
- 题目数据保证 `arr` 是一个山脉数组

## 题解

山峰数组的定义是
$$
arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
$$
分解开就是

* 对于索引$i$前的元素都是前一项小于后一项
* 对于索引$i$后的元素就是前一项大于后一项

利用二分查找的思想，可以实现

```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 2;
        int ans = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] < arr[mid+1])
                left = mid + 1;
            else {
                ans = mid;
                right = mid - 1;
            }
        }
        return ans;
    }
}
```

* 时间复杂度：$O(log(N))$
* 空间复杂度：$O(1)$