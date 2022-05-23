class Solution {
  public int findMaxForm(String[] strs, int m, int n) {
    int[][] dp = new int[m + 1][n + 1];
    for (String str : strs) {
      int num0 = (int) str.chars().filter(x -> x == '0').count();
      int num1 = str.length() - num0;

      int[][] nextDp = new int[m + 1][n + 1];
      for (int i = 0; i <= m; ++i) {
        for (int j = 0; j <= n; ++j) {
          if (i - 1 >= 0) {
            nextDp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
          }
          if (j - 1 >= 0) {
            nextDp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
          }
          if (i >= num0 && j >= num1) {
            nextDp[i][j] = Math.max(dp[i][j], dp[i - num0][j - num1] + 1);
          }
        }
      }

      dp = nextDp;
    }

    return dp[m][n];
  }
}
