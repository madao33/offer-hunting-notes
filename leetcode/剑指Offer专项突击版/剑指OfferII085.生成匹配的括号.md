## [剑指OfferII085.生成匹配的括号](https://leetcode-cn.com/problems/IDBivT/)

难度中等22收藏分享切换为英文接收动态反馈

正整数 `n` 代表生成括号的对数，请设计一个函数，用于能够生成所有可能的并且 **有效的** 括号组合。

 

**示例 1：**

```
输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]
```

**示例 2：**

```
输入：n = 1
输出：["()"]
```

 

**提示：**

- `1 <= n <= 8`

## 题解

需要注意为了实现有效的括号组合，需要进行剪枝判断：

* 开括号是否大于要求的括号组数
* 闭括号是否大于开括号

以上两种情况需要去除掉

> 至于为啥这里不需要撤销掉括号，这一点不太了解

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        if (n <= 0) return ans;
        dfs(n, ans, "", 0, 0);
        return ans;
    }

    public void dfs(int n, List<String> ans, String path, int open, int close) {
        if (open > n || close > open) return ;

        if (path.length() == 2 * n) {
            ans.add(path);
            return ;
        }

        dfs(n, ans, path + "(", open + 1, close);
        dfs(n, ans, path + ")", open, close + 1);
    }
}
```

