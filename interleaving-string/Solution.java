class Solution {
  public boolean isInterleave(String s1, String s2, String s3) {
    if (s3.length() != s1.length() + s2.length()) {
      return false;
    }

    boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
    for (int i = 0; i <= s1.length(); ++i) {
      for (int j = 0; j <= s2.length(); ++j) {
        dp[i][j] =
            (i == 0 && j == 0)
                || (i != 0 && s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j])
                || (j != 0 && s3.charAt(i + j - 1) == s2.charAt(j - 1) && dp[i][j - 1]);
      }
    }

    return dp[s1.length()][s2.length()];
  }
}
