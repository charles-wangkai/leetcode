class Solution {
  public int minDistance(String word1, String word2) {
    return word1.length() + word2.length() - 2 * computeMaxCommonSubsequence(word1, word2);
  }

  int computeMaxCommonSubsequence(String word1, String word2) {
    int[][] dp = new int[word1.length() + 1][word2.length() + 1];
    for (int i = 1; i <= word1.length(); ++i) {
      for (int j = 1; j <= word2.length(); ++j) {
        dp[i][j] =
            (word1.charAt(i - 1) == word2.charAt(j - 1))
                ? (dp[i - 1][j - 1] + 1)
                : Math.max(dp[i - 1][j], dp[i][j - 1]);
      }
    }

    return dp[word1.length()][word2.length()];
  }
}
