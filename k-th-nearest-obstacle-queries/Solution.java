import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public int[] resultsArray(int[][] queries, int k) {
    int[] result = new int[queries.length];
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    for (int i = 0; i < result.length; ++i) {
      pq.offer(Math.abs(queries[i][0]) + Math.abs(queries[i][1]));
      if (pq.size() == k + 1) {
        pq.poll();
      }

      result[i] = (pq.size() == k) ? pq.peek() : -1;
    }

    return result;
  }
}