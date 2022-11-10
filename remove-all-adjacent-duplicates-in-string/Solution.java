import java.util.Stack;
import java.util.stream.Collectors;

class Solution {
  public String removeDuplicates(String s) {
    Stack<Character> stack = new Stack<>();
    for (char letter : s.toCharArray()) {
      if (!stack.empty() && stack.peek() == letter) {
        stack.pop();
      } else {
        stack.push(letter);
      }
    }

    return stack.stream().map(String::valueOf).collect(Collectors.joining());
  }
}
