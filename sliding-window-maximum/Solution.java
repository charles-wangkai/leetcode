import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public int[] maxSlidingWindow(int[] nums, int k) {
    int[] result = new int[nums.length - k + 1];
    Deque<Integer> indices = new ArrayDeque<>();
    for (int i = 0; i < nums.length; ++i) {
      while (!indices.isEmpty() && indices.peekFirst() <= i - k) {
        indices.pollFirst();
      }
      while (!indices.isEmpty() && nums[indices.peekLast()] <= nums[i]) {
        indices.pollLast();
      }
      indices.offerLast(i);

      if (i - k + 1 >= 0) {
        result[i - k + 1] = nums[indices.peekFirst()];
      }
    }

    return result;
  }
}
