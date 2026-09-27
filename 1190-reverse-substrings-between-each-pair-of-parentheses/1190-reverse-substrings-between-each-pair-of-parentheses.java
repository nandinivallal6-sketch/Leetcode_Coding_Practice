import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == ')') {
                StringBuilder temp = new StringBuilder();
                // Pop until matching '(' is found
                while (!stack.isEmpty() && stack.peek() != '(') {
                    temp.append(stack.pop());
                }
                // Remove the '('
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                // Push reversed substring back onto stack
                for (int i = 0; i < temp.length(); i++) {
                    stack.push(temp.charAt(i));
                }
            } else {
                stack.push(c);
            }
        }

        // Reconstruct the final string
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        return result.toString();
    }
}