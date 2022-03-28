# [剑指OfferII113.课程顺序](https://leetcode-cn.com/problems/QA2IGt/)

难度中等10收藏分享切换为英文接收动态反馈

现在总共有 `numCourses` 门课需要选，记为 `0` 到 `numCourses-1`。

给定一个数组 `prerequisites` ，它的每一个元素 `prerequisites[i]` 表示两门课程之间的先修顺序。 例如 `prerequisites[i] = [ai, bi]` 表示想要学习课程 `ai` ，需要先完成课程 `bi` 。

请根据给出的总课程数  `numCourses` 和表示先修顺序的 `prerequisites` 得出一个可行的修课序列。

可能会有多个正确的顺序，只要任意返回一种就可以了。如果不可能完成所有课程，返回一个空数组。

 

**示例 1:**

```
输入: numCourses = 2, prerequisites = [[1,0]] 
输出: [0,1]
解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
```

**示例 2:**

```
输入: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
输出: [0,1,2,3] or [0,2,1,3]
解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
```

**示例 3:**

```
输入: numCourses = 1, prerequisites = [] 
输出: [0]
解释: 总共 1 门课，直接修第一门课就可。
```

 

**提示:**

- `1 <= numCourses <= 2000`
- `0 <= prerequisites.length <= numCourses * (numCourses - 1)`
- `prerequisites[i].length == 2`
- `0 <= ai, bi < numCourses`
- `ai != bi`
- `prerequisites` 中不存在重复元素

## 题解

拓扑排序和BFS遍历

```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int [] in=new int[numCourses];
        HashMap<Integer,List<Integer>> map=new HashMap<>();
        for(int pre[]:prerequisites){//统计所有课程的入度（存入in数组）以及后置节点（存入map）
            List<Integer> list = map.getOrDefault(pre[1], new ArrayList<>());
            list.add(pre[0]);
            map.put(pre[1],list);
            in[pre[0]]++;
        }
        Queue<Integer> queue=new ArrayDeque<>();
        for(int i=0;i<numCourses;i++){
            if(in[i]==0){//入度为0的节点入队
                queue.offer(i);
            }
        }
        int []result=new int[numCourses];
        int index=0;
        while(!queue.isEmpty()){
            Integer poll = queue.poll();
            result[index++]=poll;//进修poll课程
            //将该课程的后置课程节点入度减一
            List<Integer> list = map.get(poll);
            if(list==null) continue;//没有后直节点 继续循环
            for(Integer course:list){
                in[course]--;
                if(in[course]==0){
                    queue.offer(course);
                }
            }
        }
        if(index!=numCourses) return new int[0];
        return result;
    }
}
```

