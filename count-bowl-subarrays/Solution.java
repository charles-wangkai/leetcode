import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public long bowlSubarrays(int[] nums) {
    int result = 0;
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < nums.length; ++i) {
      while (!stack.isEmpty() && stack.peek() < nums[i]) {
        stack.pop();

        if (!stack.isEmpty()) {
          ++result;
        }
      }
      stack.push(nums[i]);
    }

    return result;
  }
}