import java.util.Arrays;

class Solution {
  public long maxDifference(int[] nums) {
    long[] dp = new long[nums.length + 1];
    Arrays.fill(dp, Long.MIN_VALUE);
    dp[dp.length - 1] = 0;

    for (int i = dp.length - 2; i >= 0; --i) {
      int sum = 0;
      for (int j = 1; j <= 2 && i + j < dp.length; ++j) {
        sum += nums[i + j - 1];
        dp[i] = Math.max(dp[i], sum + ((sum % 3 == 0) ? -1 : 1) * dp[i + j]);
      }
    }

    return dp[0];
  }
}