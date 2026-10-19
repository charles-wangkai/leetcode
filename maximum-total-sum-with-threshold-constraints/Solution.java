import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
  public long maxSum(int[] nums, int[] threshold) {
    int[] sortedIndices =
        IntStream.range(0, threshold.length)
            .boxed()
            .sorted(Comparator.comparing(i -> threshold[i]))
            .mapToInt(Integer::intValue)
            .toArray();

    long result = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    int step = 1;
    int pos = 0;
    while (true) {
      while (pos != sortedIndices.length && threshold[sortedIndices[pos]] <= step) {
        pq.offer(nums[sortedIndices[pos]]);
        ++pos;
      }
      if (pq.isEmpty()) {
        return result;
      }

      result += pq.poll();

      ++step;
    }
  }
}