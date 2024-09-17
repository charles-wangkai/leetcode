import java.util.Arrays;

class Solution {
  public long maxScore(int[] a, int[] b) {
    long[] dp = new long[a.length + 1];
    Arrays.fill(dp, Long.MIN_VALUE);
    dp[0] = 0;

    for (int bi : b) {
      for (int j = dp.length - 1; j >= 1; --j) {
        if (dp[j - 1] != Long.MIN_VALUE) {
          dp[j] = Math.max(dp[j], dp[j - 1] + (long) a[j - 1] * bi);
        }
      }
    }

    return dp[dp.length - 1];
  }
}