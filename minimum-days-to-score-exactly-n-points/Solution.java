import java.util.Arrays;

class Solution {
  public int minDays(int n) {
    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int i = 1; i < dp.length; ++i) {
      int sum = 0;
      for (int j = 1; ; ++j) {
        sum += j;
        if (sum > i) {
          break;
        }

        dp[i] = Math.min(dp[i], ((sum == i) ? 0 : (dp[i - sum] + 1)) + j);
      }
    }

    return dp[n];
  }
}