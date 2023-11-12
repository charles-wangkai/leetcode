class Solution {
  static final int MODULUS = 1_000_000_007;

  public int stringCount(int n) {
    int[][][] dp = new int[3][2][2];
    dp[0][0][0] = 1;

    for (int i = 0; i < n; ++i) {
      int[][][] nextDp = new int[3][2][2];
      for (int j = 0; j < 3; ++j) {
        for (int k = 0; k < 2; ++k) {
          for (int p = 0; p < 2; ++p) {
            nextDp[j][k][p] = addMod(nextDp[j][k][p], multiplyMod(dp[j][k][p], 23));
            nextDp[Math.min(2, j + 1)][k][p] =
                addMod(nextDp[Math.min(2, j + 1)][k][p], dp[j][k][p]);
            nextDp[j][Math.min(1, k + 1)][p] =
                addMod(nextDp[j][Math.min(1, k + 1)][p], dp[j][k][p]);
            nextDp[j][k][Math.min(1, p + 1)] =
                addMod(nextDp[j][k][Math.min(1, p + 1)], dp[j][k][p]);
          }
        }
      }

      dp = nextDp;
    }

    return dp[2][1][1];
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
