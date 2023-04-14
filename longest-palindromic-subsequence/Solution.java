class Solution {
  public int longestPalindromeSubseq(String s) {
    int[][] dp = new int[s.length()][s.length()];
    for (int i = 0; i < s.length(); ++i) {
      dp[i][i] = 1;
    }

    for (int length = 2; length <= s.length(); ++length) {
      for (int beginIndex = 0; beginIndex + length <= s.length(); ++beginIndex) {
        int endIndex = beginIndex + length - 1;

        if (s.charAt(beginIndex) == s.charAt(endIndex)) {
          dp[beginIndex][endIndex] = dp[beginIndex + 1][endIndex - 1] + 2;
        } else {
          dp[beginIndex][endIndex] =
              Math.max(dp[beginIndex + 1][endIndex], dp[beginIndex][endIndex - 1]);
        }
      }
    }

    return dp[0][s.length() - 1];
  }
}
