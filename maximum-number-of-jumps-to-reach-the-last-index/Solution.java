import java.util.Arrays;

class Solution {
  public int maximumJumps(int[] nums, int target) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, -1);
    dp[0] = 0;

    for (int i = 1; i < dp.length; ++i) {
      for (int j = 0; j < i; ++j) {
        if (dp[j] != -1 && Math.abs(nums[i] - nums[j]) <= target) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    return dp[dp.length - 1];
  }
}
