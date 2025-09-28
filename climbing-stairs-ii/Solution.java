import java.util.Arrays;

class Solution {
  public int climbStairs(int n, int[] costs) {
    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 0; i < dp.length; ++i) {
      for (int j = i + 1; j <= i + 3 && j < dp.length; ++j) {
        dp[j] = Math.min(dp[j], dp[i] + (costs[j - 1] + (j - i) * (j - i)));
      }
    }

    return dp[dp.length - 1];
  }
}