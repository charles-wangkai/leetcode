import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maximumRows(int[][] mat, int cols) {
    int n = mat[0].length;

    int result = 0;
    for (int mask = 0; mask < 1 << n; ++mask) {
      int mask_ = mask;
      if (Integer.bitCount(mask) == cols) {
        result =
            Math.max(
                result,
                (int)
                    Arrays.stream(mat)
                        .filter(
                            line ->
                                IntStream.range(0, n)
                                    .allMatch(c -> line[c] == 0 || (mask_ & (1 << c)) != 0))
                        .count());
      }
    }

    return result;
  }
}