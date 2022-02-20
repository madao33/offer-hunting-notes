import java.util.Stack;

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                calc(stack, token);
            } else stack.push(Integer.parseInt(token));
        }
        return stack.pop();
    }

    public void calc(Stack<Integer> stack, String operator) {
        int num2 = stack.pop(), num1 = stack.pop();
        if (operator.equals("+")) stack.push(num1 + num2);
        else if (operator.equals("-")) stack.push(num1 - num2);
        else if (operator.equals("*")) stack.push(num1 * num2);
        else stack.push(num1 / num2);
    }
}