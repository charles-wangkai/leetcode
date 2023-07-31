class Solution {
  public int minimumDeleteSum(String s1, String s2) {
    int[][] dp = new int[s1.length() + 1][s2.length() + 1];
    for (int i = 0; i <= s1.length(); ++i) {
      for (int j = 0; j <= s2.length(); ++j) {
        if (i != 0 || j != 0) {
          dp[i][j] = Integer.MAX_VALUE;
          if (i != 0 && j != 0 && s1.charAt(i - 1) == s2.charAt(j - 1)) {
            dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
          }
          if (i != 0) {
            dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + s1.charAt(i - 1));
          }
          if (j != 0) {
            dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + s2.charAt(j - 1));
          }
        }
      }
    }

    return dp[s1.length()][s2.length()];
  }
}
