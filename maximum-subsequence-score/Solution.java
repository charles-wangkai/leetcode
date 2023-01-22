import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
  public long maxScore(int[] nums1, int[] nums2, int k) {
    int[] sortedIndices =
        IntStream.range(0, nums1.length)
            .boxed()
            .sorted(Comparator.comparing((Integer i) -> nums2[i]).reversed())
            .mapToInt(Integer::intValue)
            .toArray();

    long result = -1;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    long sum = 0;
    for (int index : sortedIndices) {
      pq.offer(nums1[index]);
      sum += nums1[index];

      if (pq.size() == k + 1) {
        sum -= pq.poll();
      }

      if (pq.size() == k) {
        result = Math.max(result, sum * nums2[index]);
      }
    }

    return result;
  }
}
