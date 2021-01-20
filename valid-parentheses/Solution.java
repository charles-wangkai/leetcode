import java.util.Stack;

class Solution {
  public boolean isValid(String s) {
    Stack<Character> leftBrackets = new Stack<>();
    for (char ch : s.toCharArray()) {
      if (ch == '(' || ch == '[' || ch == '{') {
        leftBrackets.push(ch);
      } else {
        if (leftBrackets.empty()) {
          return false;
        }
        char leftBracket = leftBrackets.pop();
        if ((ch == ')' && leftBracket != '(')
            || (ch == ']' && leftBracket != '[')
            || (ch == '}' && leftBracket != '{')) {
          return false;
        }
      }
    }

    return leftBrackets.empty();
  }
}
