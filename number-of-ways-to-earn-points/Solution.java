class Solution {
  static final int MODULUS = 1_000_000_007;

  public int waysToReachTarget(int target, int[][] types) {
    int[] dp = new int[target + 1];
    dp[0] = 1;
    for (int[] type : types) {
      for (int i = dp.length - 1; i >= 0; --i) {
        for (int j = 1; j <= type[0]; ++j) {
          int prev = i - j * type[1];
          if (prev >= 0) {
            dp[i] = addMod(dp[i], dp[prev]);
          }
        }
      }
    }

    return dp[dp.length - 1];
  }

  static int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
