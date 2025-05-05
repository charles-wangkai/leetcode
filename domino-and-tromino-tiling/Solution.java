class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numTilings(int n) {
    int[][] dp = new int[n + 1][3];
    dp[0][0] = 1;
    for (int i = 1; i < dp.length; ++i) {
      dp[i][0] =
          addMod(
              addMod(dp[i - 1][0], (i >= 2) ? dp[i - 2][0] : 0),
              addMod(dp[i - 1][1], dp[i - 1][2]));
      dp[i][1] = addMod(dp[i - 1][2], (i >= 2) ? dp[i - 2][0] : 0);
      dp[i][2] = addMod(dp[i - 1][1], (i >= 2) ? dp[i - 2][0] : 0);
    }

    return dp[n][0];
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
