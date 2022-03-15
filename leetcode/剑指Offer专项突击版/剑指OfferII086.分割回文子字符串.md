# [剑指OfferII086.分割回文子字符串](https://leetcode-cn.com/problems/M99OJA/)

难度中等16收藏分享切换为英文接收动态反馈

给定一个字符串 `s` ，请将 `s` 分割成一些子串，使每个子串都是 **回文串** ，返回 s 所有可能的分割方案。

**回文串** 是正着读和反着读都一样的字符串。

 

**示例 1：**

```
输入：s = "google"
输出：[["g","o","o","g","l","e"],["g","oo","g","l","e"],["goog","l","e"]]
```

**示例 2：**

```
输入：s = "aab"
输出：[["a","a","b"],["aa","b"]]
```

**示例 3：**

```
输入：s = "a"
输出：[["a"]]
```

 

**提示：**

- `1 <= s.length <= 16`
- `s `仅由小写英文字母组成

## 题解

完全没有理解

```java
class Solution {
    public String[][] partition(String s) {
        //动态规划+回溯
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        //初始化
        for(int i=0; i<len; i++){
            dp[i][i] = true;
        }
        char[] chs = s.toCharArray();
        //动态方程：dp[i][j] = chs[i] == chs[j] && dp[i+1][j-1];
        for(int i=1; i<len; i++){
            for(int j=0; j<len-i; j++){
                dp[j][j+i] = i == 1 ? chs[j] == chs[j+i] : chs[j] == chs[j+i] && dp[j+1][j+i-1];
            }
        }
        List<List<String>> lsit = new ArrayList<>();
        backtrack(0,lsit,new ArrayList<String>(),dp,s,len);
        String[][] arrS = new String[lsit.size()][];
        for(int i=0; i<arrS.length; i++){
            List<String> _list = lsit.get(i);
            int _len = _list.size();
            arrS[i] = new String[_len];
            for(int j=0; j<_len; j++) arrS[i][j] = _list.get(j);
        }
        return arrS;
    }
    private void backtrack(int j, List<List<String>> lsit, List<String> _list, boolean[][] dp, String s, int len){
        if(j > len-1){
            lsit.add(new ArrayList<>(_list));
        }else{
            for(int i=j; i<len; i++){
                if(!dp[j][i]) continue;
                _list.add(s.substring(j,i+1));
                backtrack(i+1,lsit,_list,dp,s,len);
                _list.remove(_list.size()-1);
            }
        }
    }
}
```

