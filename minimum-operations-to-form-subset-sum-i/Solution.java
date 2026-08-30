import java.util.Arrays;

class Solution {
  public int minOperations(int[] nums, int sum) {
    int[] dp = new int[sum + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int x : nums) {
      for (int i = sum; i >= 0; --i) {
        if (dp[i] != Integer.MAX_VALUE) {
          for (int operCount = 0, value = x; i + value < dp.length; ++operCount, value *= 2) {
            dp[i + value] = Math.min(dp[i + value], dp[i] + operCount);
          }
          for (int operCount = 1, value = x / 2; value != 0; ++operCount, value /= 2) {
            if (i + value < dp.length) {
              dp[i + value] = Math.min(dp[i + value], dp[i] + operCount);
            }
          }
        }
      }
    }

    return (dp[sum] == Integer.MAX_VALUE) ? -1 : dp[sum];
  }
}