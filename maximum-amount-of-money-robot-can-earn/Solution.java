import java.util.Arrays;

class Solution {
  static final int SKIP_LIMIT = 2;

  public int maximumAmount(int[][] coins) {
    int m = coins.length;
    int n = coins[0].length;

    int[][][] dp = new int[m][n][SKIP_LIMIT + 1];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        Arrays.fill(dp[r][c], Integer.MIN_VALUE);
      }
    }

    dp[0][0][0] = coins[0][0];
    if (coins[0][0] < 0) {
      dp[0][0][1] = 0;
    }

    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        for (int i = 0; i <= SKIP_LIMIT; ++i) {
          if (dp[r][c][i] != Integer.MIN_VALUE) {
            if (r != m - 1) {
              dp[r + 1][c][i] = Math.max(dp[r + 1][c][i], dp[r][c][i] + coins[r + 1][c]);
              if (coins[r + 1][c] < 0 && i != SKIP_LIMIT) {
                dp[r + 1][c][i + 1] = Math.max(dp[r + 1][c][i + 1], dp[r][c][i]);
              }
            }
            if (c != n - 1) {
              dp[r][c + 1][i] = Math.max(dp[r][c + 1][i], dp[r][c][i] + coins[r][c + 1]);
              if (coins[r][c + 1] < 0 && i != SKIP_LIMIT) {
                dp[r][c + 1][i + 1] = Math.max(dp[r][c + 1][i + 1], dp[r][c][i]);
              }
            }
          }
        }
      }
    }

    return Arrays.stream(dp[m - 1][n - 1]).max().getAsInt();
  }
}