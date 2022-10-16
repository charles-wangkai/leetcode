import java.util.Arrays;

class Solution {
  public int minDifficulty(int[] jobDifficulty, int d) {
    if (jobDifficulty.length < d) {
      return -1;
    }

    int[][] dp = new int[jobDifficulty.length + 1][d + 1];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    dp[0][0] = 0;

    for (int length = 1; length < dp.length; ++length) {
      int maxDifficulty = -1;
      for (int i = length - 1; i >= 0; --i) {
        maxDifficulty = Math.max(maxDifficulty, jobDifficulty[i]);

        for (int day = 1; day <= d; ++day) {
          if (dp[i][day - 1] != Integer.MAX_VALUE) {
            dp[length][day] = Math.min(dp[length][day], dp[i][day - 1] + maxDifficulty);
          }
        }
      }
    }

    return dp[jobDifficulty.length][d];
  }
}
