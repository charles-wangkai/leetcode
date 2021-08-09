import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public int minStoneSum(int[] piles, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    int result = 0;
    for (int pile : piles) {
      pq.offer(pile);
      result += pile;
    }

    for (int i = 0; i < k; ++i) {
      int head = pq.poll();
      int rest = head - head / 2;

      pq.offer(rest);
      result -= head - rest;
    }

    return result;
  }
}
