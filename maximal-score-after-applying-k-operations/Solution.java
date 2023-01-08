import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public long maxKelements(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    for (int num : nums) {
      pq.offer(num);
    }

    long result = 0;
    for (int i = 0; i < k; ++i) {
      int head = pq.poll();
      result += head;
      pq.offer((head + 2) / 3);
    }

    return result;
  }
}
