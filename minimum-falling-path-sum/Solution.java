import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minFallingPathSum(int[][] matrix) {
    int n = matrix.length;

    int[] dp = matrix[0];
    for (int r = 1; r < n; ++r) {
      int[] nextDp = new int[n];
      for (int c = 0; c < n; ++c) {
        int c_ = c;
        int[] dp_ = dp;
        nextDp[c] =
            IntStream.rangeClosed(-1, 1)
                    .map(i -> c_ + i)
                    .filter(prevC -> prevC >= 0 && prevC < n)
                    .map(prevC -> dp_[prevC])
                    .min()
                    .getAsInt()
                + matrix[r][c];
      }

      dp = nextDp;
    }

    return Arrays.stream(dp).min().getAsInt();
  }
}
