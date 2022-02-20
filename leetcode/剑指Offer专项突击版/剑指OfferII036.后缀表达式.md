# [剑指OfferII036.后缀表达式](https://leetcode-cn.com/problems/8Zf90G/)

难度中等12收藏分享切换为英文接收动态反馈

根据[ 逆波兰表示法](https://baike.baidu.com/item/逆波兰式/128437)，求该后缀表达式的计算结果。

有效的算符包括 `+`、`-`、`*`、`/` 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

 

**说明：**

- 整数除法只保留整数部分。
- 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。

 

**示例 1：**

```
输入：tokens = ["2","1","+","3","*"]
输出：9
解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
```

**示例 2：**

```
输入：tokens = ["4","13","5","/","+"]
输出：6
解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
```

**示例 3：**

```
输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
输出：22
解释：
该算式转化为常见的中缀算术表达式为：
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
```

 

**提示：**

- `1 <= tokens.length <= 104`
- `tokens[i]` 要么是一个算符（`"+"`、`"-"`、`"*"` 或 `"/"`），要么是一个在范围 `[-200, 200]` 内的整数

 

**逆波兰表达式：**

逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。

- 平常使用的算式则是一种中缀表达式，如 `( 1 + 2 ) * ( 3 + 4 )` 。
- 该算式的逆波兰表达式写法为 `( ( 1 2 + ) ( 3 4 + ) * )` 。

逆波兰表达式主要有以下两个优点：

- 去掉括号后表达式无歧义，上式即便写成 `1 2 + 3 4 + * `也可以依据次序计算出正确结果。
- 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。

## 题解

对于后缀表达式，可以看做是每个运算符处理其前面的两个数字的和，然后再次计算，这种先进后出的方式，可以考虑栈来处理:

* 如果不是运算符，将字符转换为数字存储在栈中
* 如果是运算符，取栈中的两个数字，栈顶下的数字为`num1`，栈顶数字为`num2`，结果就是`num1 operator num2`，得到的结果继续存储在栈中

最后输出栈顶元素，即为后缀表达式的计算结果

```java
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/"))
                calc(stack, token);
            else stack.push(Integer.parseInt(token));
        }
        return stack.peek();
    }

    public void calc(Stack<Integer> stack, String operator) {
        int num2 = stack.pop(), num1 = stack.pop();
        if (operator.equals("+")) stack.push(num1 + num2);
        else if (operator.equals("-")) stack.push(num1 - num2);
        else if (operator.equals("*")) stack.push(num1 * num2);
        else stack.push(num1 / num2);
    }
}
```

* 时间复杂度：$O(n)$
* 空间复杂度：$O(n)$