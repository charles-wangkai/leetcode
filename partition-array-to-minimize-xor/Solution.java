import java.util.Arrays;

class Solution {
  public int minXor(int[] nums, int k) {
    int[][] dp = new int[nums.length + 1][k + 1];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    dp[0][0] = 0;

    for (int i = 1; i < dp.length; ++i) {
      for (int j = 1; j <= k; ++j) {
        int xor = 0;
        for (int prevI = i - 1; prevI >= 0; --prevI) {
          xor ^= nums[prevI];
          dp[i][j] = Math.min(dp[i][j], Math.max(xor, dp[prevI][j - 1]));
        }
      }
    }

    return dp[nums.length][k];
  }
}