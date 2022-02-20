#### [剑指OfferII035.最小时间差](https://leetcode-cn.com/problems/569nqc/)

难度中等8

给定一个 24 小时制（小时:分钟 **"HH:MM"**）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。

 

**示例 1：**

```
输入：timePoints = ["23:59","00:00"]
输出：1
```

**示例 2：**

```
输入：timePoints = ["00:00","23:59","00:00"]
输出：0
```

 

**提示：**

- `2 <= timePoints <= 2 * 104`
- `timePoints[i]` 格式为 **"HH:MM"**

## 题解

首先可以根据给定的字符串列表，将每个字符串代表的时间转换为数字，可以使用`Integer.parseInt()`函数，并且获取字符串子串可以使用`substring()`函数，然后对处理的时间字符串进行排序，然后对比计算相邻两个时间的时间差，获得最小的时间差，如果时间差判断得到0，可以直接返回0，最后，判断最开始的时间和最后的时间

```java
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int len = timePoints.size();
        int[][] time = new int[len][2];
        int i = 0;
        for (String s : timePoints) {
            time[i][0] = Integer.parseInt(s.substring(0, 2));
            time[i++][1] = Integer.parseInt(s.substring(3));
        }
        Arrays.sort(time, (a, b) ->{
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        int minute = 24*60;
        for (i = 1; i < len; i++) {
            int num = 0;
            num += ((time[i][0] - time[i-1][0]) * 60);
            num += (time[i][1] - time[i-1][1]);
            if (num == 0) return 0;
            minute = Math.min(minute, num);
        }
        return Math.min(minute, ((time[0][0] + 24 - time[len-1][0]) * 60) + (time[0][1] - time[len-1][1]));
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(n)$