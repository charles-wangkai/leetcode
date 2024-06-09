import java.util.Arrays;

class Solution {
  public int maximumLength(int[] nums, int k) {
    int[][] dp = new int[nums.length][k + 1];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], -1);
    }

    for (int i = 0; i < dp.length; ++i) {
      dp[i][0] = 1;

      for (int j = 0; j < dp[i].length; ++j) {
        for (int prevI = 0; prevI < i; ++prevI) {
          int prevJ = j - ((nums[i] == nums[prevI]) ? 0 : 1);
          if (prevJ != -1 && dp[prevI][prevJ] != -1) {
            dp[i][j] = Math.max(dp[i][j], dp[prevI][prevJ] + 1);
          }
        }
      }
    }

    int result = 0;
    for (int i = 0; i < dp.length; ++i) {
      for (int j = 0; j < dp[i].length; ++j) {
        result = Math.max(result, dp[i][j]);
      }
    }

    return result;
  }
}