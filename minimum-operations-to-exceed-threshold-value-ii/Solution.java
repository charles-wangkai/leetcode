import java.util.PriorityQueue;

class Solution {
  public int minOperations(int[] nums, int k) {
    PriorityQueue<Long> pq = new PriorityQueue<>();
    for (int num : nums) {
      pq.offer((long) num);
    }

    int result = 0;
    while (pq.peek() < k) {
      long x = pq.poll();
      long y = pq.poll();

      pq.offer(2 * x + y);
      ++result;
    }

    return result;
  }
}