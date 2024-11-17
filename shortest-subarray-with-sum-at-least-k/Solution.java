import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public int shortestSubarray(int[] nums, int k) {
    long[] prefixSums = new long[nums.length + 1];
    for (int i = 1; i < prefixSums.length; ++i) {
      prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
    }

    int result = Integer.MAX_VALUE;
    Deque<Integer> indices = new ArrayDeque<>();
    for (int i = 0; i < prefixSums.length; ++i) {
      while (!indices.isEmpty() && prefixSums[i] - prefixSums[indices.peekFirst()] >= k) {
        result = Math.min(result, i - indices.pollFirst());
      }

      while (!indices.isEmpty() && prefixSums[i] <= prefixSums[indices.peekLast()]) {
        indices.pollLast();
      }

      indices.offerLast(i);
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }
}
