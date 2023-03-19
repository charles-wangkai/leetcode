import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public long findScore(int[] nums) {
    long result = 0;
    boolean[] marked = new boolean[nums.length];
    PriorityQueue<Integer> pq =
        new PriorityQueue<>(Comparator.comparing((Integer i) -> nums[i]).thenComparing(i -> i));
    for (int i = 0; i < nums.length; ++i) {
      pq.offer(i);
    }
    while (!pq.isEmpty()) {
      int head = pq.poll();
      if (!marked[head]) {
        result += nums[head];

        for (int i = -1; i <= 1; ++i) {
          int next = head + i;
          if (next >= 0 && next < marked.length) {
            marked[next] = true;
          }
        }
      }
    }

    return result;
  }
}
