# [剑指OfferII087.复原IP](https://leetcode-cn.com/problems/0on3uN/)

难度中等18收藏分享切换为英文接收动态反馈

给定一个只包含数字的字符串 `s` ，用以表示一个 IP 地址，返回所有可能从 `s` 获得的 **有效 IP 地址** 。你可以按任何顺序返回答案。

**有效 IP 地址** 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 `0`），整数之间用 `'.'` 分隔。

例如："0.1.2.201" 和 "192.168.1.1" 是 **有效** IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 **无效** IP 地址。

 

**示例 1：**

```
输入：s = "25525511135"
输出：["255.255.11.135","255.255.111.35"]
```

**示例 2：**

```
输入：s = "0000"
输出：["0.0.0.0"]
```

**示例 3：**

```
输入：s = "1111"
输出：["1.1.1.1"]
```

**示例 4：**

```
输入：s = "010010"
输出：["0.10.0.10","0.100.1.0"]
```

**示例 5：**

```
输入：s = "10203040"
输出：["10.20.30.40","102.0.30.40","10.203.0.40"]
```

 

**提示：**

- `0 <= s.length <= 3000`
- `s` 仅由数字组成

## 题解

```java
class Solution {
    List<String> res = new ArrayList<String>();
    List<String> path = new ArrayList<String>();
    int IP_SEG = 4;
    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        if (len>3*IP_SEG)return res;//字符串长度过长：对于ipv4，超过3×4
        backtrack(s,0,len);
        return res;

    }
    public void backtrack(String s, int index, int len){
        if (index>len-1 && path.size()==IP_SEG){//出口：将整个字符串遍历完，且已有四段（ipv4）
            //加入res
            StringBuffer sb = new StringBuffer();
            for (int i=0;i<IP_SEG;i++){
                sb.append(path.get(i) + ".");
            }
            sb.deleteCharAt(sb.length()-1);
            res.add(sb.toString());
            //
            return;
        }
        //限制递归树中每个节点的分支≤3，ipv4地址每段数字一定为3位数及以下
        int n = (len-index)>=3 ? index+3 : len;

        for (int i = index;i<n;i++){
            String temp = s.substring(index,i+1);
            int inttemp = Integer.valueOf(temp);
            //判断每一段是否符合规矩
            if (inttemp>255 || (inttemp>0 && temp.charAt(0)-'0'==0) || (inttemp==0 && temp.length()>1))continue;
            path.add(temp);//符合，则加入
            backtrack(s,i+1,len);//递归
            path.remove(path.size()-1);//回溯
        }
    }
}

```

