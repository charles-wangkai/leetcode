import java.util.Arrays;

class Solution {
  public int validSubarraySplit(int[] nums) {
    int[] dp = new int[nums.length + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 1; i < dp.length; ++i) {
      for (int j = 1; j <= i; ++j) {
        if (dp[i - j] != Integer.MAX_VALUE && gcd(nums[i - 1], nums[i - j]) != 1) {
          dp[i] = Math.min(dp[i], dp[i - j] + 1);
        }
      }
    }

    return (dp[dp.length - 1] == Integer.MAX_VALUE) ? -1 : dp[dp.length - 1];
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
