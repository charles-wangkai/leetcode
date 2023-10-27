import java.util.PriorityQueue;

class Solution {
  public int makePrefSumNonNegative(int[] nums) {
    int result = 0;
    long sum = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int num : nums) {
      sum += num;
      pq.offer(num);
      if (sum < 0) {
        ++result;
        sum -= pq.poll();
      }
    }

    return result;
  }
}
