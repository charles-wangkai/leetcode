class Solution {
  public int maxProductPath(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    long[][] minProducts = new long[m][n];
    long[][] maxProducts = new long[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (r == 0 && c == 0) {
          minProducts[0][0] = grid[0][0];
          maxProducts[0][0] = grid[0][0];
        } else {
          minProducts[r][c] = Long.MAX_VALUE;
          maxProducts[r][c] = Long.MIN_VALUE;

          if (r != 0) {
            update(minProducts, maxProducts, grid, r, c, -1, 0);
          }
          if (c != 0) {
            update(minProducts, maxProducts, grid, r, c, 0, -1);
          }
        }
      }
    }

    return (maxProducts[m - 1][n - 1] >= 0)
        ? (int) (maxProducts[m - 1][n - 1] % 1_000_000_007)
        : -1;
  }

  void update(
      long[][] minProducts,
      long[][] maxProducts,
      int[][] grid,
      int r,
      int c,
      int offsetR,
      int offsetC) {
    int prevR = r + offsetR;
    int prevC = c + offsetC;

    long product1 = minProducts[prevR][prevC] * grid[r][c];
    long product2 = maxProducts[prevR][prevC] * grid[r][c];

    minProducts[r][c] = Math.min(minProducts[r][c], Math.min(product1, product2));
    maxProducts[r][c] = Math.max(maxProducts[r][c], Math.max(product1, product2));
  }
}