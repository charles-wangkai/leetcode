class Solution {
  public long minCost(int m, int n, int[][] waitCost) {
    int[][] costs = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        costs[r][c] =
            (r + 1) * (c + 1)
                + (((r == 0 && c == 0) || (r == m - 1 && c == n - 1)) ? 0 : waitCost[r][c]);
      }
    }

    long[][] dp = new long[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (r == 0 && c == 0) {
          dp[r][c] = costs[r][c];
        } else {
          dp[r][c] = Long.MAX_VALUE;
          if (r != 0) {
            dp[r][c] = Math.min(dp[r][c], dp[r - 1][c] + costs[r][c]);
          }
          if (c != 0) {
            dp[r][c] = Math.min(dp[r][c], dp[r][c - 1] + costs[r][c]);
          }
        }
      }
    }

    return dp[m - 1][n - 1];
  }
}