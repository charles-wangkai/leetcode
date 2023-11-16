class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfWays(int n, int m, int k, int[] source, int[] dest) {
    int[][] dp = new int[2][2];
    dp[(source[0] == dest[0]) ? 1 : 0][(source[1] == dest[1]) ? 1 : 0] = 1;

    for (int i = 0; i < k; ++i) {
      int[][] nextDp = new int[2][2];

      // 0,0 -> 0,0
      nextDp[0][0] = addMod(nextDp[0][0], multiplyMod(dp[0][0], n - 2));
      nextDp[0][0] = addMod(nextDp[0][0], multiplyMod(dp[0][0], m - 2));

      // 0,0 -> 0,1
      nextDp[0][1] = addMod(nextDp[0][1], dp[0][0]);

      // 0,0 -> 1,0
      nextDp[1][0] = addMod(nextDp[1][0], dp[0][0]);

      // 0,0 -> 1,1

      // ---

      // 0,1 -> 0,0
      nextDp[0][0] = addMod(nextDp[0][0], multiplyMod(dp[0][1], m - 1));

      // 0,1 -> 0,1
      nextDp[0][1] = addMod(nextDp[0][1], multiplyMod(dp[0][1], n - 2));

      // 0,1 -> 1,0

      // 0,1 -> 1,1
      nextDp[1][1] = addMod(nextDp[1][1], dp[0][1]);

      // ---

      // 1,0 -> 0,0
      nextDp[0][0] = addMod(nextDp[0][0], multiplyMod(dp[1][0], n - 1));

      // 1,0 -> 0,1

      // 1,0 -> 1,0
      nextDp[1][0] = addMod(nextDp[1][0], multiplyMod(dp[1][0], m - 2));

      // 1,0 -> 1,1
      nextDp[1][1] = addMod(nextDp[1][1], dp[1][0]);

      // ---

      // 1,1 -> 0,0

      // 1,1 -> 0,1
      nextDp[0][1] = addMod(nextDp[0][1], multiplyMod(dp[1][1], n - 1));

      // 1,1 -> 1,0
      nextDp[1][0] = addMod(nextDp[1][0], multiplyMod(dp[1][1], m - 1));

      // 1,1 -> 1,1

      dp = nextDp;
    }

    return dp[1][1];
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
