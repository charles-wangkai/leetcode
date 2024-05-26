class Solution {
  static final int MODULUS = 1_000_000_007;

  public int checkRecord(int n) {
    int[][][] dp = new int[n + 1][2][3];
    dp[0][0][0] = 1;

    for (int i = 1; i <= n; i++) {
      dp[i][0][0] = addMod(addMod(dp[i - 1][0][0], dp[i - 1][0][1]), dp[i - 1][0][2]);
      dp[i][0][1] = dp[i - 1][0][0];
      dp[i][0][2] = dp[i - 1][0][1];

      dp[i][1][0] =
          addMod(
              addMod(addMod(dp[i - 1][0][0], dp[i - 1][0][1]), dp[i - 1][0][2]),
              addMod(addMod(dp[i - 1][1][0], dp[i - 1][1][1]), dp[i - 1][1][2]));
      dp[i][1][1] = dp[i - 1][1][0];
      dp[i][1][2] = dp[i - 1][1][1];
    }

    int result = 0;
    for (int i = 0; i < dp[n].length; ++i) {
      for (int j = 0; j < dp[n][i].length; ++j) {
        result = addMod(result, dp[n][i][j]);
      }
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
