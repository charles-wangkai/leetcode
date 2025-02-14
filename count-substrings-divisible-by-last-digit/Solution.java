class Solution {
  public long countSubstrings(String s) {
    int[][] dp = new int[10][];
    for (int i = 1; i < dp.length; ++i) {
      dp[i] = new int[i];
    }

    long result = 0;
    for (char c : s.toCharArray()) {
      int digit = c - '0';

      int[][] nextDp = new int[dp.length][];
      for (int i = 1; i < nextDp.length; ++i) {
        nextDp[i] = new int[i];

        for (int j = 0; j < dp[i].length; ++j) {
          nextDp[i][(j * 10 + digit) % i] += dp[i][j];
        }
        ++nextDp[i][digit % i];
      }

      dp = nextDp;

      if (digit != 0) {
        result += dp[digit][0];
      }
    }

    return result;
  }
}