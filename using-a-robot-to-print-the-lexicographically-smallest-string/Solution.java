import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public String robotWithString(String s) {
    char[] rightMins = new char[s.length()];
    for (int i = rightMins.length - 1; i >= 0; --i) {
      rightMins[i] =
          (char)
              Math.min(
                  s.charAt(i),
                  ((i == rightMins.length - 1) ? Character.MAX_VALUE : rightMins[i + 1]));
    }

    StringBuilder result = new StringBuilder();
    Deque<Character> stack = new ArrayDeque<>();
    int index = 0;
    while (!stack.isEmpty() || index != s.length()) {
      if (index == s.length() || (!stack.isEmpty() && stack.peek() <= rightMins[index])) {
        result.append(stack.pop());
      } else {
        stack.push(s.charAt(index));
        ++index;
      }
    }

    return result.toString();
  }
}
