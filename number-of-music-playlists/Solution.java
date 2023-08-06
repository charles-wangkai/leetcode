class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numMusicPlaylists(int n, int goal, int k) {
    int[][] dp = new int[goal + 1][n + 1];
    dp[0][0] = 1;

    for (int i = 1; i <= goal; ++i) {
      for (int j = 1; j <= n; ++j) {
        dp[i][j] =
            addMod(multiplyMod(dp[i - 1][j - 1], j), multiplyMod(dp[i - 1][j], Math.max(0, j - k)));
      }
    }

    return dp[goal][n];
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
