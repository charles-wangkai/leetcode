import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
  public int maxWidthRamp(int[] A) {
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < A.length; i++) {
      if (stack.isEmpty() || A[i] < A[stack.peek()]) {
        stack.push(i);
      }
    }

    int result = 0;
    for (int i = A.length - 1; i > result; i--) {
      while (!stack.isEmpty() && A[i] >= A[stack.peek()]) {
        result = Math.max(result, i - stack.pop());
      }
    }
    return result;
  }
}
