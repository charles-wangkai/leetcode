class Solution {
  public int findMaxForm(String[] strs, int m, int n) {
    int[][] dp = new int[m + 1][n + 1];
    for (String str : strs) {
      int num0 = (int) str.chars().filter(x -> x == '0').count();
      int num1 = str.length() - num0;

      for (int i = m; i >= 0; --i) {
        for (int j = n; j >= 0; --j) {
          if (i - 1 >= 0) {
            dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
          }
          if (j - 1 >= 0) {
            dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
          }
          if (i >= num0 && j >= num1) {
            dp[i][j] = Math.max(dp[i][j], dp[i - num0][j - num1] + 1);
          }
        }
      }
    }

    return dp[m][n];
  }
}
