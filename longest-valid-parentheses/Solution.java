import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public int longestValidParentheses(String s) {
    int maxLength = 0;
    Deque<Integer> leftIndices = new ArrayDeque<>();
    leftIndices.add(-1);
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == '(') {
        leftIndices.push(i);
      } else {
        leftIndices.pop();
        if (leftIndices.isEmpty()) {
          leftIndices.push(i);
        } else {
          maxLength = Math.max(maxLength, i - leftIndices.peek());
        }
      }
    }

    return maxLength;
  }
}
