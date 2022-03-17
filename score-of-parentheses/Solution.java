import java.util.Stack;

class Solution {
  public int scoreOfParentheses(String s) {
    Stack<Integer> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      if (c == '(') {
        stack.push(0);
      } else {
        int top = stack.pop();
        if (top == 0) {
          stack.push(1);
        } else {
          stack.pop();
          stack.push(top * 2);
        }

        if (stack.size() >= 2) {
          int top1 = stack.pop();
          int top2 = stack.pop();
          if (top2 == 0) {
            stack.push(top2);
            stack.push(top1);
          } else {
            stack.push(top1 + top2);
          }
        }
      }
    }

    return stack.peek();
  }
}
