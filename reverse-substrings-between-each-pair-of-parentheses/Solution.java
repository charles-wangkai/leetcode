import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public String reverseParentheses(String s) {
    Deque<StringBuilder> stack = new ArrayDeque<>();
    stack.push(new StringBuilder());

    for (char c : s.toCharArray()) {
      if (c == '(') {
        stack.push(new StringBuilder());
      } else if (c == ')') {
        StringBuilder top = stack.pop();
        stack.peek().append(top.reverse());
      } else {
        stack.peek().append(c);
      }
    }

    return stack.peek().toString();
  }
}
