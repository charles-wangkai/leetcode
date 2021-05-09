import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
  public boolean isPossible(int[] target) {
    int oneCount = 0;
    long pqSum = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    for (int x : target) {
      if (x == 1) {
        ++oneCount;
      } else {
        pq.offer(x);
        pqSum += x;
      }
    }

    while (!pq.isEmpty()) {
      int max = pq.poll();
      pqSum -= max;

      if (max <= pqSum + oneCount || pqSum + oneCount == 0) {
        return false;
      }

      long prev = max % (pqSum + oneCount);
      if (prev == 0 && pqSum + oneCount != 1) {
        return false;
      }
      if (prev == 1 || pqSum + oneCount == 1) {
        ++oneCount;
      } else {
        pq.offer((int) prev);
        pqSum += prev;
      }
    }

    return true;
  }
}
