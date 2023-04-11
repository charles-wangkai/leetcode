import java.util.Stack;
import java.util.stream.Collectors;

class Solution {
  public String removeStars(String s) {
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      if (c == '*') {
        stack.pop();
      } else {
        stack.push(c);
      }
    }

    return stack.stream().map(String::valueOf).collect(Collectors.joining());
  }
}
