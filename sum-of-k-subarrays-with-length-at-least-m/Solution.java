import java.util.Arrays;

class Solution {
  public int maxSum(int[] nums, int k, int m) {
    int[][] dp = new int[k + 1][m + 1];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], Integer.MIN_VALUE);
    }
    dp[0][0] = 0;

    for (int value : nums) {
      int[][] nextDp = new int[k + 1][m + 1];
      for (int i = 0; i < nextDp.length; ++i) {
        Arrays.fill(nextDp[i], Integer.MIN_VALUE);
      }

      for (int i = 0; i < dp.length; ++i) {
        for (int j = 0; j < dp[i].length; ++j) {
          if (dp[i][j] != Integer.MIN_VALUE) {
            if (j == 0) {
              if (i != dp.length - 1) {
                nextDp[i + 1][j + 1] = Math.max(nextDp[i + 1][j + 1], dp[i][j] + value);
              }
              nextDp[i][0] = Math.max(nextDp[i][0], dp[i][j]);
            } else if (j < m) {
              nextDp[i][j + 1] = Math.max(nextDp[i][j + 1], dp[i][j] + value);
            } else {
              nextDp[i][j] = Math.max(nextDp[i][j], dp[i][j] + value);
              if (i != dp.length - 1) {
                nextDp[i + 1][1] = Math.max(nextDp[i + 1][1], dp[i][j] + value);
              }
              nextDp[i][0] = Math.max(nextDp[i][0], dp[i][j]);
            }
          }
        }
      }

      dp = nextDp;
    }

    return Math.max(dp[k][0], dp[k][m]);
  }
}
