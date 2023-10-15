class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numWays(int steps, int arrLen) {
    int[] dp = new int[Math.min(arrLen, steps / 2 + 1)];
    dp[0] = 1;

    for (int step = 0; step < steps; ++step) {
      int[] nextDp = new int[dp.length];
      for (int i = 0; i < nextDp.length; ++i) {
        for (int offset = -1; offset <= 1; ++offset) {
          int prevIndex = i + offset;
          if (prevIndex >= 0 && prevIndex < dp.length) {
            nextDp[i] = addMod(nextDp[i], dp[prevIndex]);
          }
        }
      }

      dp = nextDp;
    }

    return dp[0];
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
