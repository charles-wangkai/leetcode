import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public boolean isValid(String s) {
    Deque<Character> leftBrackets = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
      if (c == '(' || c == '[' || c == '{') {
        leftBrackets.push(c);
      } else {
        if (leftBrackets.isEmpty()) {
          return false;
        }
        char leftBracket = leftBrackets.pop();
        if ((c == ')' && leftBracket != '(')
            || (c == ']' && leftBracket != '[')
            || (c == '}' && leftBracket != '{')) {
          return false;
        }
      }
    }

    return leftBrackets.isEmpty();
  }
}
