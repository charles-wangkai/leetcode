import java.util.Stack;
import java.util.stream.Collectors;

class Solution {
    public String makeGood(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (!stack.empty() && stack.peek() != ch
                    && Character.toLowerCase(stack.peek()) == Character.toLowerCase(ch)) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        return stack.stream().map(String::valueOf).collect(Collectors.joining());
    }
}