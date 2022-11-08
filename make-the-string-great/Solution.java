import java.util.Stack;
import java.util.stream.Collectors;

class Solution {
  public String makeGood(String s) {
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      if (!stack.empty()
          && stack.peek() != c
          && Character.toLowerCase(stack.peek()) == Character.toLowerCase(c)) {
        stack.pop();
      } else {
        stack.push(c);
      }
    }

    return stack.stream().map(String::valueOf).collect(Collectors.joining());
  }
}
