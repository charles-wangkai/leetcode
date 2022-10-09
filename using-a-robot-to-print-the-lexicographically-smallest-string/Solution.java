import java.util.Stack;

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
    Stack<Character> stack = new Stack<>();
    int index = 0;
    while (!stack.empty() || index != s.length()) {
      if (index == s.length() || (!stack.empty() && stack.peek() <= rightMins[index])) {
        result.append(stack.pop());
      } else {
        stack.push(s.charAt(index));
        ++index;
      }
    }

    return result.toString();
  }
}
