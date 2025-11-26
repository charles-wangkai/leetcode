class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfPaths(int[][] grid, int k) {
    int m = grid.length;
    int n = grid[0].length;

    int[][][] dp = new int[m][n][k];
    dp[0][0][grid[0][0] % k] = 1;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        for (int p = 0; p < k; ++p) {
          if (c != n - 1) {
            dp[r][c + 1][(p + grid[r][c + 1]) % k] =
                addMod(dp[r][c + 1][(p + grid[r][c + 1]) % k], dp[r][c][p]);
          }
          if (r != m - 1) {
            dp[r + 1][c][(p + grid[r + 1][c]) % k] =
                addMod(dp[r + 1][c][(p + grid[r + 1][c]) % k], dp[r][c][p]);
          }
        }
      }
    }

    return dp[m - 1][n - 1][0];
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
