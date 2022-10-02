class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numRollsToTarget(int n, int k, int target) {
    int[] dp = new int[target + 1];
    dp[0] = 1;

    for (int i = 0; i < n; ++i) {
      int[] nextDp = new int[dp.length];
      for (int j = 0; j < nextDp.length; ++j) {
        for (int d = 1; d <= k && d <= j; ++d) {
          nextDp[j] = addMod(nextDp[j], dp[j - d]);
        }
      }

      dp = nextDp;
    }

    return dp[target];
  }

  static int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
