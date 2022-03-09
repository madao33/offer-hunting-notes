#### [剑指OfferII074.合并区间](https://leetcode-cn.com/problems/SsGoHC/)

难度中等15收藏分享切换为英文接收动态反馈

以数组 `intervals` 表示若干个区间的集合，其中单个区间为 `intervals[i] = [starti, endi]` 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

 

**示例 1：**

```
输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
```

**示例 2：**

```
输入：intervals = [[1,4],[4,5]]
输出：[[1,5]]
解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
```

 

**提示：**

- `1 <= intervals.length <= 104`
- `intervals[i].length == 2`
- `0 <= starti <= endi <= 104`

## 题解

首先可以根据每个区间的起始`start`排序，然后从第一个区间开始判断，如果下一个区间的起始小于当前区间的结尾，那么将当前区间的结尾改为下一个区间的结尾；否则添加下一个区间。

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0)
            return new int[0][2];
        Arrays.sort(intervals, new Comparator<int[]> () {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });

        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; i++) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

}
```

## 补充知识

关于`comparator`：[Comparator的用法](https://blog.csdn.net/u012250875/article/details/55126531)

`List`和数组相互转换

* `List`转换为数组

  ```java
  ArrayList<String> list = new ArrayList<String>();
  String[] strings = new String[list.size()];
  list.toArray(strings);
  ```

* 数组转换为`List`

  ```java
  import java.util.Arrays;
  import java.util.List;
  
  String[] s = {"a", "b", "c"};
  List<String> list = Arrays.asList(s);
  ```

  