import java.util.Arrays;

class Solution {
  public int maximumLength(int[] nums, int k) {
    int[] lastIndices = new int[k];
    Arrays.fill(lastIndices, -1);

    int result = -1;
    int[][] dp = new int[nums.length][nums.length];
    for (int i = 0; i < dp.length; ++i) {
      for (int j = i + 1; j < dp.length; ++j) {
        dp[i][j] = 2;
        if (lastIndices[nums[j] % k] != -1) {
          dp[i][j] = Math.max(dp[i][j], dp[lastIndices[nums[j] % k]][i] + 1);
        }

        result = Math.max(result, dp[i][j]);
      }

      lastIndices[nums[i] % k] = i;
    }

    return result;
  }
}