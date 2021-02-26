import java.util.Stack;

class Solution {
  public boolean validateStackSequences(int[] pushed, int[] popped) {
    Stack<Integer> stack = new Stack<>();
    int pushedIndex = 0;
    for (int target : popped) {
      while (stack.empty() || stack.peek() != target) {
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
