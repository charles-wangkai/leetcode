import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minFallingPathSum(int[][] matrix) {
    int n = matrix.length;

    int[] minSums = matrix[0];
    for (int r = 1; r < n; ++r) {
      int[] nextMinSums = new int[n];
      for (int c = 0; c < n; ++c) {
        int c_ = c;
        int[] minSums_ = minSums;
        nextMinSums[c] =
            IntStream.rangeClosed(-1, 1)
                    .map(i -> c_ + i)
                    .filter(prevC -> prevC >= 0 && prevC < n)
                    .map(prevC -> minSums_[prevC])
                    .min()
                    .getAsInt()
                + matrix[r][c];
      }

      minSums = nextMinSums;
    }

    return Arrays.stream(minSums).min().getAsInt();
  }
}
