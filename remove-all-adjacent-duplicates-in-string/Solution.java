import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public String removeDuplicates(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char letter : s.toCharArray()) {
      if (!stack.isEmpty() && stack.peek() == letter) {
        stack.pop();
      } else {
        stack.push(letter);
      }
    }

    List<Character> result = new ArrayList<>(stack);
    Collections.reverse(result);

    return result.stream().map(String::valueOf).collect(Collectors.joining());
  }
}
