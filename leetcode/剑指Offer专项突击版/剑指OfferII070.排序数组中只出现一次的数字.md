#### [剑指OfferII070.排序数组中只出现一次的数字](https://leetcode-cn.com/problems/skFtm2/)

难度中等20收藏分享切换为英文接收动态反馈

给定一个只包含整数的有序数组 `nums` ，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字。

 

**示例 1:**

```
输入: nums = [1,1,2,3,3,4,4,8,8]
输出: 2
```

**示例 2:**

```
输入: nums =  [3,3,7,7,10,11,11]
输出: 10
```

 

 



**提示:**

- `1 <= nums.length <= 105`
- `0 <= nums[i] <= 105`

## 题解

### 异或

首先想到的暴力解法就是直接异或

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++)
            ans ^= nums[i];
        return ans;
    }
}
```

* 时间复杂度：$O(N)$
* 空间复杂度：$O(1)$

### 二分查找

由于只出现一次的元素所在下标 x的左边有偶数个元素，因此下标 x一定是偶数，可以在偶数下标范围内二分查找。二分查找的目标是找到满足 $nums[x] \neq nums[x+1]$ 的最小的偶数下标 x，则下标 x处的元素是只出现一次的元素。

初始时，二分查找的左边界是 0，右边界是数组的最大偶数下标，由于数组的长度是奇数，因此数组的最大偶数下标等于数组的长度减 1。每次取左右边界的平均值 $mid$ 作为待判断的下标，如果  $mid$  是奇数则将 $mid$ 减 1，确保 $mid$ 是偶数，比较 $nums[mid]$ 和 $nums[mid+1]$ 是否相等，如果相等则 $mid<x$，调整左边界，否则 $mid\geq x$，调整右边界。调整边界之后继续二分查找，直到确定下标 $x$ 的值。

得到下标 $x$ 的值之后，$nums[x]$ 即为只出现一次的元素。

细节

考虑 $mid$ 和 1 按位与运算的结果，其中`&` 是按位与运算符：

* 当 $mid$是偶数时，`mid&1=0`
* 当$mid$是奇数时，`mid&1=1`

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 2;
        while(left < right) {
            int mid = (left + right) / 2;
            mid -= (mid&1);
            if (nums[mid] == nums[mid + 1]) 
                left = mid + 2;
            else
                right = mid;
        }
        return nums[left];
    }
}
```

* 时间复杂度：$O(log(N))$
* 空间复杂度：$O(1)$