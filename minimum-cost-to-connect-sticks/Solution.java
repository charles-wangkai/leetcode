import java.util.PriorityQueue;

class Solution {
  public int connectSticks(int[] sticks) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int stick : sticks) {
      pq.offer(stick);
    }

    int result = 0;
    while (pq.size() != 1) {
      int cost = pq.poll() + pq.poll();
      result += cost;

      pq.offer(cost);
    }

    return result;
  }
}
