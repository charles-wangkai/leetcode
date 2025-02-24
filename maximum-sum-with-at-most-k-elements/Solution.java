import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
  public long maxSum(int[][] grid, int[] limits, int k) {
    int[][] sorted =
        IntStream.range(0, grid.length)
            .mapToObj(
                r ->
                    Arrays.stream(grid[r])
                        .boxed()
                        .sorted(Comparator.reverseOrder())
                        .limit(limits[r])
                        .mapToInt(Integer::intValue)
                        .toArray())
            .toArray(int[][]::new);

    int[] indices = new int[sorted.length];

    PriorityQueue<Integer> pq =
        new PriorityQueue<>(
            Comparator.<Integer, Integer>comparing(r -> sorted[r][indices[r]]).reversed());
    for (int r = 0; r < sorted.length; ++r) {
      if (indices[r] != sorted[r].length) {
        pq.offer(r);
      }
    }

    long result = 0;
    for (int i = 0; i < k; ++i) {
      int r = pq.poll();
      result += sorted[r][indices[r]];

      ++indices[r];
      if (indices[r] != sorted[r].length) {
        pq.offer(r);
      }
    }

    return result;
  }
}
