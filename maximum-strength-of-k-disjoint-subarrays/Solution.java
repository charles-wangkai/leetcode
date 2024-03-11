import java.util.Arrays;

class Solution {
  public long maximumStrength(int[] nums, int k) {
    long[][] dp = new long[k + 1][2];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], Long.MIN_VALUE);
    }
    dp[0][0] = 0;

    long result = Long.MIN_VALUE;
    for (int num : nums) {
      long[][] nextDp = new long[k + 1][2];
      for (int i = 0; i < nextDp.length; ++i) {
        Arrays.fill(nextDp[i], Long.MIN_VALUE);
      }

      for (int i = 0; i < dp.length; ++i) {
        if (dp[i][0] != Long.MIN_VALUE) {
          nextDp[i][0] = Math.max(nextDp[i][0], dp[i][0]);

          if (i != dp.length - 1) {
            nextDp[i + 1][1] =
                Math.max(
                    nextDp[i + 1][1], dp[i][0] + (long) num * (k - i) * ((i % 2 == 0) ? 1 : -1));
          }
        }
        if (dp[i][1] != Long.MIN_VALUE) {
          nextDp[i][1] =
              Math.max(nextDp[i][1], dp[i][1] + (long) num * (k - i + 1) * ((i % 2 == 0) ? -1 : 1));

          nextDp[i][0] = Math.max(nextDp[i][0], dp[i][1]);

          if (i != dp.length - 1) {
            nextDp[i + 1][1] =
                Math.max(
                    nextDp[i + 1][1], dp[i][1] + (long) num * (k - i) * ((i % 2 == 0) ? 1 : -1));
          }
        }
      }

      result = Math.max(result, Math.max(nextDp[k][0], nextDp[k][1]));

      dp = nextDp;
    }

    return result;
  }
}