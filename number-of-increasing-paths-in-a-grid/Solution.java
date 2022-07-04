import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int countPaths(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[] sortedPositions =
        IntStream.range(0, m * n)
            .boxed()
            .sorted(Comparator.comparing(i -> grid[i / n][i % n]))
            .mapToInt(x -> x)
            .toArray();

    int[][] dp = new int[m][n];
    for (int position : sortedPositions) {
      int r = position / n;
      int c = position % n;

      dp[r][c] = 1;
      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = r + R_OFFSETS[i];
        int adjC = c + C_OFFSETS[i];
        if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n && grid[adjR][adjC] < grid[r][c]) {
          dp[r][c] = addMod(dp[r][c], dp[adjR][adjC]);
        }
      }
    }

    int result = 0;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        result = addMod(result, dp[r][c]);
      }
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}