class Solution {
  public long sellingWood(int m, int n, int[][] prices) {
    long[][] dp = new long[m + 1][n + 1];
    for (int[] price : prices) {
      dp[price[0]][price[1]] = price[2];
    }

    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        for (int k = 1; k < i; ++k) {
          dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
        }

        for (int k = 1; k < j; ++k) {
          dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
        }
      }
    }

    return dp[m][n];
  }
}