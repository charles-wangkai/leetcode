import java.util.Arrays;

class Solution {
  public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
    int[][] dp = new int[target + 1][n + 1];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    dp[0][0] = 0;

    for (int h = 0; h < houses.length; ++h) {
      int[][] nextDp = new int[target + 1][n + 1];
      for (int i = 0; i < nextDp.length; ++i) {
        Arrays.fill(nextDp[i], Integer.MAX_VALUE);
      }

      for (int c = 1; c <= n; ++c) {
        if (houses[h] == 0 || c == houses[h]) {
          for (int i = 1; i <= target; ++i) {
            for (int prevC = 0; prevC <= n; ++prevC) {
              int prevI = i - ((prevC == c) ? 0 : 1);

              if (dp[prevI][prevC] != Integer.MAX_VALUE) {
                nextDp[i][c] =
                    Math.min(
                        nextDp[i][c], dp[prevI][prevC] + ((houses[h] == 0) ? cost[h][c - 1] : 0));
              }
            }
          }
        }
      }

      dp = nextDp;
    }

    return Arrays.stream(dp[target]).filter(x -> x != Integer.MAX_VALUE).min().orElse(-1);
  }
}