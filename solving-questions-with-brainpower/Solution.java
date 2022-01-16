import java.util.Arrays;

class Solution {
  public long mostPoints(int[][] questions) {
    long[] dp = new long[questions.length];
    for (int i = 0; i < dp.length; ++i) {
      if (i + 1 != dp.length) {
        dp[i + 1] = Math.max(dp[i + 1], dp[i]);
      }

      dp[i] += questions[i][0];
      if (i + questions[i][1] + 1 < dp.length) {
        dp[i + questions[i][1] + 1] = Math.max(dp[i + questions[i][1] + 1], dp[i]);
      }
    }

    return Arrays.stream(dp).max().getAsLong();
  }
}