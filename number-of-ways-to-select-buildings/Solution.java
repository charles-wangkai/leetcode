import java.util.Arrays;

class Solution {
  public long numberOfWays(String s) {
    long[][] dp = new long[4][2];
    Arrays.fill(dp[0], 1);
    long[][] nextDp = new long[4][2];
    for (char c : s.toCharArray()) {
      copy(dp, nextDp);

      for (int i = 1; i < nextDp.length; ++i) {
        if (c == '0') {
          nextDp[i][0] += dp[i - 1][1];
        } else {
          nextDp[i][1] += dp[i - 1][0];
        }
      }

      copy(nextDp, dp);
    }

    return dp[3][0] + dp[3][1];
  }

  void copy(long[][] src, long[][] dest) {
    for (int i = 0; i < dest.length; ++i) {
      for (int j = 0; j < dest[i].length; ++j) {
        dest[i][j] = src[i][j];
      }
    }
  }
}