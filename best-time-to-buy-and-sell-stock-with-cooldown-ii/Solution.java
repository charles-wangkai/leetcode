class Solution {
  public int maxProfit(int[] prices, int cooldown, int[] costs) {
    int[] dp = new int[prices.length + 1];
    for (int i = 0; i < prices.length; ++i) {
      dp[i + 1] = dp[i];

      for (int j = 0; j < i; ++j) {
        dp[i + 1] =
            Math.max(
                dp[i + 1], (prices[i] - prices[j] - costs[i - j]) + dp[Math.max(0, j - cooldown)]);
      }
    }

    return dp[dp.length - 1];
  }
}