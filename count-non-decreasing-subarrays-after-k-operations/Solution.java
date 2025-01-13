// https://leetcode.com/problems/count-non-decreasing-subarrays-after-k-operations/solutions/6267498/python-mono-decreasing-deque-on-by-lee21-nywu/

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public long countNonDecreasingSubarrays(int[] nums, int k) {
    for (int i = 0, j = nums.length - 1; i < j; ++i, --j) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
    }

    long result = 0;
    Deque<Integer> deque = new ArrayDeque<>();
    int beginIndex = 0;
    long cost = 0;
    for (int endIndex = 0; endIndex < nums.length; ++endIndex) {
      while (!deque.isEmpty() && nums[deque.peekLast()] < nums[endIndex]) {
        int toIndex = deque.pollLast();
        int fromIndex = deque.isEmpty() ? (beginIndex - 1) : deque.peekLast();
        cost += (long) (toIndex - fromIndex) * (nums[endIndex] - nums[toIndex]);
      }

      deque.offerLast(endIndex);

      while (cost > k) {
        cost -= nums[deque.peekFirst()] - nums[beginIndex];
        if (deque.peekFirst() == beginIndex) {
          deque.pollFirst();
        }

        ++beginIndex;
      }

      result += endIndex - beginIndex + 1;
    }

    return result;
  }
}
