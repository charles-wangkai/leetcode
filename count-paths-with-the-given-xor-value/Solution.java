class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countPathsWithXorValue(int[][] grid, int k) {
    int m = grid.length;
    int n = grid[0].length;

    int[][][] dp = new int[m][n][16];
    dp[0][0][grid[0][0]] = 1;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        for (int i = 0; i < 16; ++i) {
          if (c != n - 1) {
            dp[r][c + 1][i ^ grid[r][c + 1]] =
                addMod(dp[r][c + 1][i ^ grid[r][c + 1]], dp[r][c][i]);
          }
          if (r != m - 1) {
            dp[r + 1][c][i ^ grid[r + 1][c]] =
                addMod(dp[r + 1][c][i ^ grid[r + 1][c]], dp[r][c][i]);
          }
        }
      }
    }

    return dp[m - 1][n - 1][k];
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }
}