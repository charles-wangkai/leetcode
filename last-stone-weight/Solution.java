import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
  public int lastStoneWeight(int[] stones) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    for (int stone : stones) {
      pq.offer(stone);
    }

    while (pq.size() >= 2) {
      int max = pq.poll();
      int secondMax = pq.poll();

      if (max != secondMax) {
        pq.offer(max - secondMax);
      }
    }

    return (pq.size() == 1) ? pq.peek() : 0;
  }
}
