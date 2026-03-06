import java.util.Arrays;

class Solution {
  public int minCost(int n) {
    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[1] = 0;

    for (int i = 2; i < dp.length; ++i) {
      for (int j = 1; j < i; ++j) {
        dp[i] = Math.min(dp[i], dp[j] + dp[i - j] + j * (i - j));
      }
    }

    return dp[n];
  }
}