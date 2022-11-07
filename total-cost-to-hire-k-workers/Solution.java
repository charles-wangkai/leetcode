import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public long totalCost(int[] costs, int k, int candidates) {
    int leftIndex = 0;
    int rightIndex = costs.length - 1;
    PriorityQueue<Integer> pq =
        new PriorityQueue<>(Comparator.comparing((Integer i) -> costs[i]).thenComparing(i -> i));
    for (int i = 0; i < candidates && leftIndex <= rightIndex; ++i) {
      pq.offer(leftIndex);
      ++leftIndex;
    }
    for (int i = 0; i < candidates && leftIndex <= rightIndex; ++i) {
      pq.offer(rightIndex);
      --rightIndex;
    }

    long result = 0;
    for (int i = 0; i < k; ++i) {
      int index = pq.poll();
      result += costs[index];
      if (index < leftIndex) {
        if (leftIndex <= rightIndex) {
          pq.offer(leftIndex);
          ++leftIndex;
        }
      } else if (leftIndex <= rightIndex) {
        pq.offer(rightIndex);
        --rightIndex;
      }
    }

    return result;
  }
}
