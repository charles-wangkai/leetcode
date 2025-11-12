import java.util.Arrays;

class Solution {
  public int maxPathScore(int[][] grid, int k) {
    int m = grid.length;
    int n = grid[0].length;

    int[][][] dp = new int[m][n][k + 1];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        Arrays.fill(dp[r][c], -1);
      }
    }
    dp[0][0][0] = 0;

    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        for (int cost = 0; cost <= k; ++cost) {
          int prevCost = cost - ((grid[r][c] == 0) ? 0 : 1);
          if (prevCost >= 0) {
            if (r != 0 && dp[r - 1][c][prevCost] != -1) {
              dp[r][c][cost] = Math.max(dp[r][c][cost], dp[r - 1][c][prevCost] + grid[r][c]);
            }
            if (c != 0 && dp[r][c - 1][prevCost] != -1) {
              dp[r][c][cost] = Math.max(dp[r][c][cost], dp[r][c - 1][prevCost] + grid[r][c]);
            }
          }
        }
      }
    }

    return Arrays.stream(dp[m - 1][n - 1]).max().getAsInt();
  }
}