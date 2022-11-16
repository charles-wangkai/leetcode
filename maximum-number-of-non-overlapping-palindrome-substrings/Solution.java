class Solution {
  public int maxPalindromes(String s, int k) {
    boolean[][] palindromes = new boolean[s.length()][s.length()];
    for (int length = 1; length <= s.length(); ++length) {
      for (int beginIndex = 0; beginIndex + length - 1 < s.length(); ++beginIndex) {
        int endIndex = beginIndex + length - 1;
        palindromes[beginIndex][endIndex] =
            s.charAt(beginIndex) == s.charAt(endIndex)
                && (beginIndex + 1 > endIndex - 1 || palindromes[beginIndex + 1][endIndex - 1]);
      }
    }

    int[] dp = new int[s.length()];
    for (int i = 0; i < dp.length; ++i) {
      if (i != 0) {
        dp[i] = dp[i - 1];
      }

      for (int j = i - k + 1; j >= 0; --j) {
        if (palindromes[j][i]) {
          dp[i] = Math.max(dp[i], 1 + ((j == 0) ? 0 : dp[j - 1]));
        }
      }
    }

    return dp[dp.length - 1];
  }
}
