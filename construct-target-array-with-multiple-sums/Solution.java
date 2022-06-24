import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
  public boolean isPossible(int[] target) {
    long sum = Arrays.stream(target).asLongStream().sum();
    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    for (int x : target) {
      pq.offer(x);
    }

    while (true) {
      int max = pq.poll();
      if (max == 1) {
        return true;
      }

      sum -= max;

      if (max <= sum || sum == 0) {
        return false;
      }

      int prev = (int) (max % sum);
      if (prev == 0 && sum == 1) {
        prev = 1;
      }
      if (prev == 0) {
        return false;
      }

      pq.offer(prev);
      sum += prev;
    }
  }
}
