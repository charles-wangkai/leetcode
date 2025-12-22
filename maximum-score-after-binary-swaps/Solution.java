import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public long maximumScore(int[] nums, String s) {
    long result = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    for (int i = 0; i < nums.length; ++i) {
      pq.offer(nums[i]);
      if (s.charAt(i) == '1') {
        result += pq.poll();
      }
    }

    return result;
  }
}