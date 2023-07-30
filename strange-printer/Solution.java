class Solution {
  public int strangePrinter(String s) {
    int[][] dp = new int[s.length()][s.length()];
    for (int length = 1; length <= s.length(); ++length) {
      for (int beginIndex = 0; beginIndex + length <= s.length(); ++beginIndex) {
        int endIndex = beginIndex + length - 1;

        dp[beginIndex][endIndex] = getValue(dp, beginIndex, endIndex - 1) + 1;
        for (int i = beginIndex; i < endIndex; ++i) {
          if (s.charAt(i) == s.charAt(endIndex)) {
            dp[beginIndex][endIndex] =
                Math.min(
                    dp[beginIndex][endIndex],
                    getValue(dp, beginIndex, i) + getValue(dp, i + 1, endIndex - 1));
          }
        }
      }
    }

    return dp[0][s.length() - 1];
  }

  int getValue(int[][] dp, int beginIndex, int endIndex) {
    return (beginIndex <= endIndex) ? dp[beginIndex][endIndex] : 0;
  }
}
