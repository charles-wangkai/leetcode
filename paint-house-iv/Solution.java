import java.util.Arrays;

class Solution {
  public long minCost(int n, int[][] cost) {
    long[][] dp = null;
    for (int i = n / 2 - 1, j = i + 1; i >= 0; --i, ++j) {
      long[][] nextDp = new long[3][3];
      for (int c1 = 0; c1 < nextDp.length; ++c1) {
        Arrays.fill(nextDp[c1], Long.MAX_VALUE);
      }

      if (dp == null) {
        for (int c1 = 0; c1 < 3; ++c1) {
          for (int c2 = 0; c2 < 3; ++c2) {
            if (c2 != c1) {
              nextDp[c1][c2] = cost[i][c1] + cost[j][c2];
            }
          }
        }
      } else {
        for (int c1 = 0; c1 < 3; ++c1) {
          for (int c2 = 0; c2 < 3; ++c2) {
            for (int prevC1 = 0; prevC1 < 3; ++prevC1) {
              for (int prevC2 = 0; prevC2 < 3; ++prevC2) {
                if (c2 != c1
                    && prevC1 != c1
                    && prevC2 != c2
                    && dp[prevC1][prevC2] != Long.MAX_VALUE) {
                  nextDp[c1][c2] =
                      Math.min(nextDp[c1][c2], dp[prevC1][prevC2] + cost[i][c1] + cost[j][c2]);
                }
              }
            }
          }
        }
      }

      dp = nextDp;
    }

    long result = Long.MAX_VALUE;
    for (long[] dpi : dp) {
      for (long dpij : dpi) {
        result = Math.min(result, dpij);
      }
    }

    return result;
  }
}