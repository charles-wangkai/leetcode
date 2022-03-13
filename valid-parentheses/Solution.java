import java.util.Stack;

class Solution {
  public boolean isValid(String s) {
    Stack<Character> leftBrackets = new Stack<>();
    for (char c : s.toCharArray()) {
      if (c == '(' || c == '[' || c == '{') {
        leftBrackets.push(c);
      } else {
        if (leftBrackets.empty()) {
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

    return leftBrackets.empty();
  }
}
