import java.util.Arrays;

class Solution {
  public int longestSubsequence(int[] nums) {
    int limit = Arrays.stream(nums).max().getAsInt();

    int[][] dp = new int[limit + 1][limit + 1];
    for (int num : nums) {
      for (int prev = 0; prev <= limit; ++prev) {
        int diff = Math.abs(num - prev);
        dp[num][diff] = Math.max(dp[num][diff], dp[prev][diff] + 1);
      }

      for (int diff = limit - 1; diff >= 0; --diff) {
        dp[num][diff] = Math.max(dp[num][diff], dp[num][diff + 1]);
      }
    }

    int result = Integer.MIN_VALUE;
    for (int[] dpi : dp) {
      for (int dpij : dpi) {
        result = Math.max(result, dpij);
      }
    }

    return result;
  }
}