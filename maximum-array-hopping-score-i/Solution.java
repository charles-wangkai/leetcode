class Solution {
  public int maxScore(int[] nums) {
    int[] dp = new int[nums.length];
    for (int i = 1; i < dp.length; ++i) {
      for (int j = 0; j < i; ++j) {
        dp[i] = Math.max(dp[i], dp[j] + (i - j) * nums[i]);
      }
    }

    return dp[dp.length - 1];
  }
}