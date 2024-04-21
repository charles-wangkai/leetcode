import java.util.Arrays;

class Solution {
  public int minimumOperations(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[] dp = new int[11];
    for (int c = 0; c < n; ++c) {
      int[] counts = new int[11];
      for (int r = 0; r < m; ++r) {
        ++counts[grid[r][c]];
      }

      int[] nextDp = new int[dp.length];
      Arrays.fill(nextDp, Integer.MAX_VALUE);
      for (int i = 0; i < nextDp.length; ++i) {
        for (int j = 0; j < dp.length; ++j) {
          if (j != i || j == dp.length - 1) {
            nextDp[i] = Math.min(nextDp[i], dp[j] + (m - counts[i]));
          }
        }
      }

      dp = nextDp;
    }

    return Arrays.stream(dp).min().getAsInt();
  }
}