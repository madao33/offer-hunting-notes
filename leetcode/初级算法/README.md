# 初级算法

这是由 LeetCode 官方推出的经典面试题目清单，以下的题目是初级面试题目清单

## 数组

### 基础知识



### [删除排序数组中的重复项](https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2gy9m/)

给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。

由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。

将最终结果插入 nums 的前 k 个位置后返回 k 。

不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

判题标准:

系统会用下面的代码来测试你的题解:

```java
int[] nums = [...]; // 输入数组
int[] expectedNums = [...]; // 长度正确的期望答案

int k = removeDuplicates(nums); // 调用

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
```

如果所有断言都通过，那么您的题解将被通过。

 

**示例 1：**

```
输入：nums = [1,1,2]
输出：2, nums = [1,2,_]
解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
```

**示例 2：**

```
输入：nums = [0,0,1,1,1,2,2,3,3,4]
输出：5, nums = [0,1,2,3,4]
解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
```

**提示：**

0 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
nums 已按 升序 排列

#### 题解

原数组已经按照升序排列，所以相同的元素必定是相邻的，可以设置两个指针，一个指针`ans`指向不重复数组元素的最后一位，一个指针`cur`指向遍历的元素，判断两个指针指向元素是否相同

* 如果相同，则`cur++`，继续查找下一个元素
* 如果不同，那么`ans`下一位元素变更为`cur`当前指向元素

最后数组遍历结束，返回不重复数组元素的长度，`ans`指向的是不重复元素的最后一位的索引，为了获取长度，还需要+1

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length, ans = 0, cur = 1;
        while(cur < n) {
            if (nums[ans] != nums[cur])
                nums[++ans] = nums[cur];
            cur++;
        }
        return ++ans;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(1)$

![image-20220428184822895](imgs/image-20220428184822895.png)

### [买卖股票的最佳时机II](https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2zsx1/)

给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。

在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。

返回 你能获得的 最大 利润 。

 

**示例 1：**

```
输入：prices = [7,1,5,3,6,4]
输出：7
解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
     总利润为 4 + 3 = 7 。
```

**示例 2：**

```
输入：prices = [1,2,3,4,5]
输出：4
解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     总利润为 4 。
```

**示例 3：**

```
输入：prices = [7,6,4,3,1]
输出：0
解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。
```

**提示：**

- `1 <= prices.length <= 3 * 104`
- `0 <= prices[i] <= 104`

**相关标签**

- 贪心
- 数组
- 动态规划

#### 题解

如下图是表示的一个股票价格上涨曲线，要计算这段时间内股票的最大利润，可以直接将股票所有上涨时间段内的利润累加起来就得到了

![image-20220428101138044](imgs/image-20220428101138044.png)

```java
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length, ans = 0;

        for (int i = 1; i < len; i++) {
            ans += Math.max(prices[i] - prices[i-1], 0);
        }
        return ans;
        
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(1)$

![image-20220428184811158](imgs/image-20220428184811158.png)

### [旋转数组](https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2skh7/)

给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。

 

**示例 1:**

```
输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右轮转 1 步: [7,1,2,3,4,5,6]
向右轮转 2 步: [6,7,1,2,3,4,5]
向右轮转 3 步: [5,6,7,1,2,3,4]
```

**示例 2:**

```
输入：nums = [-1,-100,3,99], k = 2
输出：[3,99,-1,-100]
解释: 
向右轮转 1 步: [99,-1,-100,3]
向右轮转 2 步: [3,99,-1,-100]
```

**提示：**

- `1 <= nums.length <= 105`
- `-231 <= nums[i] <= 231 - 1`
- `0 <= k <= 105`

**进阶：**

- 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
- 你可以使用空间复杂度为` O(1) `的 原地 算法解决这个问题吗？

#### 题解

可以转换一下思路：

* 首先将整个数组进行反转
* 然后将数组`[0, k-1]`位置的元素进行反转
* 最后将剩下的`[k, length - 1]`位置的元素进行反转

```java
class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        if (len<=1)
            return ;
        reverse(nums, 0, len-1);
        reverse(nums,0, k-1);
        reverse(nums, k, len-1);
    }

    public void reverse(int[] nums, int start, int end) {
        int left = start, right = end;
        while(left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            ++left;
            --right;
        }
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(1)$

![image-20220428184623433](imgs/image-20220428184623433.png)

### [存在重复元素](https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x248f5/)

给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。

**示例 1：**

```
输入：nums = [1,2,3,1]
输出：true
```

**示例 2：**

```
输入：nums = [1,2,3,4]
输出：false
```

**示例 3：**

```
输入：nums = [1,1,1,3,3,4,3,2,4,2]
输出：true
```

**提示：**

- `1 <= nums.length <= 105`
- `-109 <= nums[i] <= 109`

**相关标签**

- 数组
- 哈希表
- 排序

#### 题解

**HashSet实现**

将元素添加到`HashSet`中，如果无法添加说明已经存在，直接返回`true`，如果数组遍历结束那么返回`false`

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            if (!set.add(num))
                return true;
        }
        return false;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(1)$

![image-20220428184517850](imgs/image-20220428184517850.png)

**先排序然后比较两个相邻的元素**

排序原数组，相同的元素必定相邻，判断相邻的元素是否相等，如果相等返回`true`，所有元素遍历之后都不相等，返回`false`

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1])
                return true;
        }
        return false;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(1)$

主要还是排序的时间开销过大导致的执行时间太高，这样对比还是利用`HashSet`更为高效

![image-20220428184203724](imgs/image-20220428184203724.png)

### [只出现一次的数字](https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x21ib6/)

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

**说明：**

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

**示例 1:**

```
输入: [2,2,1]
输出: 1
```

示例 2:

```
输入: [4,1,2,1,2]
输出: 4
```

**相关标签**

- 位运算
- 数组

#### 题解

所有的元素都出现两次，相同的元素异或为0，剩下的只出现一次的元素和0异或的结果还是其本身

```java
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums)
            res ^= num;
        return res;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(1)$

![image-20220428184147570](imgs/image-20220428184147570.png)

### [两个数组的交集 II](https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2y0c2/)

给你两个整数数组 `nums1 `和 `nums2 `，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。

 

**示例 1：**

```
输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2,2]
```

**示例 2:**

```
输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[4,9]
```

**提示：**

- `1 <= nums1.length, nums2.length <= 1000`
- `0 <= nums1[i], nums2[i] <= 1000`

**进阶：**

- 如果给定的数组已经排好序呢？你将如何优化你的算法？

- 如果 nums1 的大小比 nums2 小，哪种方法更优？
- 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？

#### 题解

首先对两个数组进行排序，然后设置两个指针分别指向排序后数组开头，在两个指针都分别不超过数组的情况下，比较两个指针指向元素的大小

* 如果相等，添加到结果数组中，两个指针都自增1
* 如果不等，指向元素小的指针自增

最后将保存结果的列表转换为数组输出

```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length, len2 = nums2.length;
        int index1 = 0, index2 = 0;
        List<Integer> ans = new ArrayList<>();
        while (index1 < len1 && index2 < len2) {
            if (nums1[index1] < nums2[index2])
                index1++;
            else if (nums1[index1] > nums2[index2])
                index2++;
            else {
                ans.add(nums1[index1]);
                index1++;
                index2++;
            }
        }

        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(1)$

![image-20220428184132539](imgs/image-20220428184132539.png)

### [加一](https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2cv1c/)

给定一个由 **整数** 组成的 **非空** 数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储**单个**数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

 

**示例 1：**

```
输入：digits = [1,2,3]
输出：[1,2,4]
解释：输入数组表示数字 123。
```

**示例 2：**

```
输入：digits = [4,3,2,1]
输出：[4,3,2,2]
解释：输入数组表示数字 4321。
```

**示例 3：**

```
输入：digits = [0]
输出：[1]
```

**提示：**

- `1 <= digits.length <= 100`
- `0 <= digits[i] <= 9`

#### 题解

这道题就是模拟加法进位，注意的是在执行加法进位的时候，需要从最后一位开始计算

* 如果有进位，那么当前位结果必定为`0`，进位为`1`
* 没有进位，就将当前结果和上一次的进位相加保存

因为可能会出现进位后数位加一的情况，所以暂存结果可以用`List`来保存，然后**逆序**输出结果至`int`数组中

```java
class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        List<Integer> res = new ArrayList<>();
        for (int i = digits.length-1; i >= 0; i--) {
            if (digits[i] + carry > 9) {
                carry = 1;
                res.add(0);
            }else {
                res.add(digits[i] + carry);
                carry = 0;
            }
        }
        if (carry != 0)
            res.add(carry);
        int[] ans = new int[res.size()];
        for (int i=res.size()-1; i>=0; i--)
            ans[res.size() - 1 - i] = res.get(i); 
        return ans;
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(n)$

![image-20220428184119444](imgs/image-20220428184119444.png)

### [移动零](https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2ba4i/)

给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

请注意 ，必须在不复制数组的情况下原地对数组进行操作。

 

**示例 1:**

```
输入: nums = [0,1,0,3,12]
输出: [1,3,12,0,0]
```

**示例 2:**

```
输入: nums = [0]
输出: [0]
```


提示:

- `1 <= nums.length <= 104`
- `-231 <= nums[i] <= 231 - 1`

**进阶：**你能尽量减少完成的操作次数吗？

#### 题解

设置一个指针`index`保存不为0的元素，然后遍历数组中的所有元素，将其保存到`index`位置，然后`index++`，最后将`index, nums.length-1`位置的数组全部改为0

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        if (n < 1)
            return ;
        int left = 0, right = 1;
        while (right < n && left < n) {
            while(left < n && nums[left] != 0)
                left++;
            right = left + 1;
            while(right < n && nums[right] == 0)
                right++;
            if (right >= n)
                return ;
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
        }
        
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(1)$

![image-20220428184104388](imgs/image-20220428184104388.png)

### [两数之和](https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2jrse/)

给定一个整数数组 `nums` 和一个整数目标值 `target`，请你在该数组中找出 **和为目标值** `target  `的那 **两个** 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

 

**示例 1：**

```
输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
```

**示例 2：**

```
输入：nums = [3,2,4], target = 6
输出：[1,2]
```

**示例 3：**

```
输入：nums = [3,3], target = 6
输出：[0,1]
```

**提示：**

- `2 <= nums.length <= 104`
- `-109 <= nums[i] <= 109`
- `-109 <= target <= 109`
- **只会存在一个有效答案**

**进阶：**你可以想出一个时间复杂度小于 O(n2) 的算法吗？

#### 题解

**HashMap法**

首先遍历一遍数组，将对应的差值和索引保存在`HashMap`中，然后再次遍历`HashMap`，如果有数组元素在`HashMap`中，取出`HashMap`中的元素以及当前遍历索引，返回结果

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(target-nums[i], i);
        }

        for (int i = 0; i < n; i++) {
            int temp = map.getOrDefault(nums[i], -1);
            if (temp != -1 && temp != i) {
                res[0] = temp;
                res[1] = i;
                break;
            }
        }

        return res;
    }
}
```

* 时间复杂度：$O(2n)$ 包括两次数组遍历
* 空间复杂度：$O(n)$ 主要是`HashMap`空间

**一次遍历**

可以通过一次遍历获取结果，可以看做前半部分的遍历是获取其中一般元素，后一半的遍历就是查看`HashMap`中是否存在和当前元素和为`target`的元素

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.get(target - nums[i]) != null)
                return new int[]{i, map.get(target-nums[i])};
            map.put(nums[i], i);
        }
        return new int[]{0, 0};
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(1)$

![image-20220428184045437](imgs/image-20220428184045437.png)

### [有效的数独](https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2f9gg/)

请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。

- 数字 1-9 在每一行只能出现一次。

- 数字 1-9 在每一列只能出现一次。
- 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）

**注意：**

- 一个有效的数独（部分已被填充）不一定是可解的。

- 只需要根据以上规则，验证已经填入的数字是否有效即可。
- 空白格用 '.' 表示。

示例 1：

![image-20220428171517947](imgs/image-20220428171517947.png)

```
输入：board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：true
```

**示例 2：**

```
输入：board = 
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：false
解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
```




提示：

- `board.length == 9`
- `board[i].length == 9`
- `board[i][j] 是一位数字（1-9）或者 '.'`



#### 题解

表格固定形状大小为`9 x 9`，需要查看每一行，每一列，以及`3 x 3` 大小表格是否有相同元素，可以设定3个2维数组分别表示对应行、列以及小网格内`0-9`是否存在，如果有任意一个出现重复元素则返回`false`，遍历结束返回`true`

```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        int n = board.length, m = board[0].length;
        int[][] row = new int[n][10], col = new int[m][10], cell = new int[9][10];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '.')
                    continue;
                int num = board[i][j] - '0';
                int k = (i/3) * 3 + (j/3);
                if (row[i][num] == 1 || col[j][num] == 1 || cell[k][num] == 1)
                    return false;
                row[i][num] = col[j][num] = cell[k][num] = 1;
            }
        }
        return true;
    }
}
```

* 时间复杂度：$O(n^2)$
* 空间复杂度：$O(10 \cdot n)$ 主要是三个二维数组

![image-20220428183921926](imgs/image-20220428183921926.png)

### [旋转图像](https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnhhkv/)

给定一个` n × n` 的二维矩阵 `matrix `表示一个图像。请你将图像顺时针旋转 90 度。

你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。**请不要** 使用另一个矩阵来旋转图像。

 

示例 1：

![img](imgs/mat1.jpg)

```
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[[7,4,1],[8,5,2],[9,6,3]]
```

**示例 2：**

![img](imgs/mat2.jpg)

```
输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
```

**提示：**

- `n == matrix.length == matrix[i].length`
- `1 <= n <= 20`
- `-1000 <= matrix[i][j] <= 1000`

#### 题解

如下图所示

![leet0048.png](imgs/1615651665-WFwYuH-leet0048.png)

```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-i][j];
                matrix[n-1-i][j] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

    }
}
```

* 时间复杂度：$O(n^2)$
* 空间复杂度：$O(1)$

![image-20220428183855572](imgs/image-20220428183855572-16511423372634.png)















