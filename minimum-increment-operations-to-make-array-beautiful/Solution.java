class Solution {
  public long minIncrementOperations(int[] nums, int k) {
    long[] dp = new long[nums.length];
    for (int i = 0; i < dp.length; ++i) {
      dp[i] =
          Math.min(
                  Math.min((i >= 3) ? dp[i - 3] : 0, (i >= 2) ? dp[i - 2] : 0),
                  (i >= 1) ? dp[i - 1] : 0)
              + Math.max(0, k - nums[i]);
    }

    return Math.min(Math.min(dp[dp.length - 3], dp[dp.length - 2]), dp[dp.length - 1]);
  }
}
