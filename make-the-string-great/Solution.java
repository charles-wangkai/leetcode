import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

class Solution {
  public String makeGood(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
      if (!stack.isEmpty()
          && stack.peek() != c
          && Character.toLowerCase(stack.peek()) == Character.toLowerCase(c)) {
        stack.pop();
      } else {
        stack.push(c);
      }
    }

    return new StringBuilder(stack.stream().map(String::valueOf).collect(Collectors.joining()))
        .reverse()
        .toString();
  }
}
