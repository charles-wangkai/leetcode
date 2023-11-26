import java.util.Arrays;

class Solution {
  public int minimumCoins(int[] prices) {
    int[] dp = new int[prices.length];
    Arrays.fill(dp, Integer.MAX_VALUE);
    for (int i = 0; i < dp.length; ++i) {
      int purchased = ((i == 0) ? 0 : dp[i - 1]) + prices[i];
      for (int j = i; j <= i + (i + 1) && j < dp.length; ++j) {
        dp[j] = Math.min(dp[j], purchased);
      }
    }

    return dp[dp.length - 1];
  }
}