import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public String removeStars(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
      if (c == '*') {
        stack.pop();
      } else {
        stack.push(c);
      }
    }

    List<Character> result = new ArrayList<>(stack);
    Collections.reverse(result);

    return result.stream().map(String::valueOf).collect(Collectors.joining());
  }
}
