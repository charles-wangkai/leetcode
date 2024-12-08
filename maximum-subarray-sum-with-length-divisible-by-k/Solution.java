import java.util.Arrays;

class Solution {
  public long maxSubarraySum(int[] nums, int k) {
    long[] dp = new long[nums.length];
    Arrays.fill(dp, Long.MIN_VALUE);

    long sum = 0;
    for (int i = 0; i < dp.length; ++i) {
      sum += nums[i];
      if (i >= k - 1) {
        dp[i] = Math.max(0, (i == k - 1) ? 0 : dp[i - k]) + sum;

        sum -= nums[i - k + 1];
      }
    }

    return Arrays.stream(dp).max().getAsLong();
  }
}