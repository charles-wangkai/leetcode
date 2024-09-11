class Solution {
  public int minOperations(String initial, String target) {
    return initial.length() + target.length() - 2 * computeMaxCommonLength(initial, target);
  }

  int computeMaxCommonLength(String s1, String s2) {
    int[][] dp = new int[s1.length() + 1][s2.length() + 1];
    for (int i = 1; i <= s1.length(); ++i) {
      for (int j = 1; j <= s2.length(); ++j) {
        dp[i][j] = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? (dp[i - 1][j - 1] + 1) : 0;
      }
    }

    int result = 0;
    for (int i = 0; i <= s1.length(); ++i) {
      for (int j = 0; j <= s2.length(); ++j) {
        result = Math.max(result, dp[i][j]);
      }
    }

    return result;
  }
}