import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public long pickGifts(int[] gifts, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    for (int gift : gifts) {
      pq.offer(gift);
    }

    for (int i = 0; i < k; ++i) {
      pq.offer(sqrt(pq.poll()));
    }

    return pq.stream().mapToInt(Integer::intValue).asLongStream().sum();
  }

  int sqrt(int x) {
    int result = (int) Math.floor(Math.sqrt(x));
    if ((result + 1) * (result + 1) <= x) {
      ++result;
    }

    return result;
  }
}
