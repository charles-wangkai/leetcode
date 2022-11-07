import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
  public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
    Collections.sort(robot);
    Arrays.sort(factory, Comparator.comparing(f -> f[0]));

    long[] dp = new long[robot.size() + 1];
    Arrays.fill(dp, Long.MAX_VALUE);
    dp[0] = 0;

    for (int[] f : factory) {
      long[] nextDp = dp.clone();
      for (int i = 0; i < dp.length; ++i) {
        if (dp[i] != Long.MAX_VALUE) {
          long sum = 0;
          for (int j = 0; j < f[1] && i + j < robot.size(); ++j) {
            sum += Math.abs(robot.get(i + j) - f[0]);
            nextDp[i + j + 1] = Math.min(nextDp[i + j + 1], dp[i] + sum);
          }
        }
      }

      dp = nextDp;
    }

    return dp[robot.size()];
  }
}
