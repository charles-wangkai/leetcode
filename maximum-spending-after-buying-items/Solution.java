import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long maxSpending(int[][] values) {
    int m = values.length;
    int n = values[0].length;

    int[] indices = new int[m];
    Arrays.fill(indices, n - 1);

    long result = 0;
    for (int i = 0; i < m * n; ++i) {
      int row =
          IntStream.range(0, indices.length)
              .filter(r -> indices[r] != -1)
              .boxed()
              .min(Comparator.comparing(r -> values[r][indices[r]]))
              .get();
      result += (i + 1L) * values[row][indices[row]];
      --indices[row];
    }

    return result;
  }
}
