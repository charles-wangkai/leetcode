import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String minRemoveToMakeValid(String s) {
    boolean[] kept = new boolean[s.length()];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < s.length(); ++i) {
      char ch = s.charAt(i);

      if (ch == '(') {
        stack.push(i);
      } else if (ch == ')') {
        if (!stack.isEmpty()) {
          kept[stack.pop()] = true;
          kept[i] = true;
        }
      } else {
        kept[i] = true;
      }
    }

    return IntStream.range(0, s.length())
        .filter(i -> kept[i])
        .mapToObj(i -> String.valueOf(s.charAt(i)))
        .collect(Collectors.joining());
  }
}
