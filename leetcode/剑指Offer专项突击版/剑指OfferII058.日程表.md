#### [剑指OfferII058.日程表](https://leetcode-cn.com/problems/fi9suh/)

难度中等14收藏分享切换为英文接收动态反馈

请实现一个 `MyCalendar` 类来存放你的日程安排。如果要添加的时间内没有其他安排，则可以存储这个新的日程安排。

`MyCalendar` 有一个 `book(int start, int end)`方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，即 `[start, end)`, 实数 `x` 的范围为，  `start <= x < end`。

当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。

每次调用 `MyCalendar.book`方法时，如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 `true`。否则，返回 `false` 并且不要将该日程安排添加到日历中。

请按照以下步骤调用 `MyCalendar` 类: `MyCalendar cal = new MyCalendar();` `MyCalendar.book(start, end)`

 

**示例:**

```
输入:
["MyCalendar","book","book","book"]
[[],[10,20],[15,25],[20,30]]
输出: [null,true,false,true]
解释: 
MyCalendar myCalendar = new MyCalendar();
MyCalendar.book(10, 20); // returns true 
MyCalendar.book(15, 25); // returns false ，第二个日程安排不能添加到日历中，因为时间 15 已经被第一个日程安排预定了
MyCalendar.book(20, 30); // returns true ，第三个日程安排可以添加到日历中，因为第一个日程安排并不包含时间 20 
```

 

 

**提示：**

- 每个测试用例，调用 `MyCalendar.book` 函数最多不超过 `1000`次。
- `0 <= start < end <= 109`

## 题解

如果需要插入的日程安排是`start`和`end`，这道题只需要查看`start`前最近的日程结尾是否超过，以及`start`之后开始的日程开始时间是否大于`end`，两者都满足，就可以插入，为了实现`key`的比较，这里需要使用到`TreeMap`。

`TreeMap`是一个能比较元素大小的`Map`集合，会对传入的`key`进行大小排序，可以使用元素自然大小排序，也可以使用集合中自定义的比较器来排序。`TreeMap`的实现是**红黑树**，暂时还没了解其底层原理，后续需要了解一下，这里列一下`TreeMap`相关的API

```java
public interface NavigableMap<K,V> extends SortedMap<K,V> {

    //返回小于key的第一个元素：
    Map.Entry<K,V> lowerEntry(K key);

    //返回小于key的第一个键：
    K lowerKey(K key);

    //返回小于等于key的第一个元素：
    Map.Entry<K,V> floorEntry(K key);

    //返回小于等于key的第一个键：
    K floorKey(K key);

    //返回大于或者等于key的第一个元素：
    Map.Entry<K,V> ceilingEntry(K key);

    //返回大于或者等于key的第一个键：
    K ceilingKey(K key);

    //返回大于key的第一个元素：
    Map.Entry<K,V> higherEntry(K key);

    //返回大于key的第一个键：
    K higherKey(K key);

    //返回集合中第一个元素：
    Map.Entry<K,V> firstEntry();

    //返回集合中最后一个元素：
    Map.Entry<K,V> lastEntry();

    //返回集合中第一个元素，并从集合中删除：
    Map.Entry<K,V> pollFirstEntry();

    //返回集合中最后一个元素，并从集合中删除：
    Map.Entry<K,V> pollLastEntry();

    //返回倒序的Map集合：
    java.util.NavigableMap<K,V> descendingMap();

    NavigableSet<K> navigableKeySet();

    //返回Map集合中倒序的Key组成的Set集合：
    NavigableSet<K> descendingKeySet();

    java.util.NavigableMap<K,V> subMap(K fromKey, boolean fromInclusive,
                                       K toKey, boolean toInclusive);

    java.util.NavigableMap<K,V> headMap(K toKey, boolean inclusive);

    java.util.NavigableMap<K,V> tailMap(K fromKey, boolean inclusive);

    SortedMap<K,V> subMap(K fromKey, K toKey);

    SortedMap<K,V> headMap(K toKey);

    SortedMap<K,V> tailMap(K fromKey);
}
```

本题题解代码

```java
class MyCalendar {
    
    private TreeMap<Integer, Integer> treemap;

    public MyCalendar() {
        treemap = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> floorPeriod = treemap.floorEntry(start);
        Map.Entry<Integer, Integer> ceilPeriod = treemap.ceilingEntry(start);

        int lastEnd = Integer.MIN_VALUE;
        int nextStart = Integer.MAX_VALUE;
        if (floorPeriod != null)
            lastEnd = floorPeriod.getValue();
        if (ceilPeriod != null)
            nextStart = ceilPeriod.getKey();

        if (lastEnd <= start && end <= nextStart) {
            treemap.put(start, end);
            return true;
        }
        return false;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
```

