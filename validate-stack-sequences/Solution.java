import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public boolean validateStackSequences(int[] pushed, int[] popped) {
    Deque<Integer> stack = new ArrayDeque<>();
    int pushedIndex = 0;
    for (int target : popped) {
      while (stack.isEmpty() || stack.peek() != target) {
        if (pushedIndex == pushed.length) {
          return false;
        }

        stack.push(pushed[pushedIndex]);
        ++pushedIndex;
      }

      stack.pop();
    }

    return true;
  }
}
