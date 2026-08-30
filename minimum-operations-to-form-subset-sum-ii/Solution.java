import java.util.Arrays;

class Solution {
  public int minOperations(int[] nums, int sum) {
    int[] dp = new int[sum + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int x : nums) {
      for (int i = sum; i >= 0; --i) {
        if (dp[i] != Integer.MAX_VALUE) {
          for (int divOperCount = 0, base = x; base != 0; ++divOperCount, base /= 2) {
            for (int mulOperCount = 0, value = base;
                i + value < dp.length;
                ++mulOperCount, value *= 2) {
              dp[i + value] = Math.min(dp[i + value], dp[i] + divOperCount + mulOperCount);
            }
          }
        }
      }
    }

    return (dp[sum] == Integer.MAX_VALUE) ? -1 : dp[sum];
  }
}