class Solution {
  static final int MODULUS = 12345;

  public int[][] constructProductMatrix(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;

    int[][] leftProducts = new int[n][m];
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < m; ++c) {
        leftProducts[r][c] = multiplyMod((c == 0) ? 1 : leftProducts[r][c - 1], grid[r][c]);
      }
    }

    int[][] rightProducts = new int[n][m];
    for (int r = 0; r < n; ++r) {
      for (int c = m - 1; c >= 0; --c) {
        rightProducts[r][c] = multiplyMod((c == m - 1) ? 1 : rightProducts[r][c + 1], grid[r][c]);
      }
    }

    int[] topProducts = new int[n];
    for (int r = 0; r < n; ++r) {
      topProducts[r] = multiplyMod((r == 0) ? 1 : topProducts[r - 1], leftProducts[r][m - 1]);
    }

    int[] bottomProducts = new int[n];
    for (int r = n - 1; r >= 0; --r) {
      bottomProducts[r] =
          multiplyMod((r == n - 1) ? 1 : bottomProducts[r + 1], leftProducts[r][m - 1]);
    }

    int[][] result = new int[n][m];
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < m; ++c) {
        result[r][c] =
            multiplyMod(
                multiplyMod(
                    (r == 0) ? 1 : topProducts[r - 1], (r == n - 1) ? 1 : bottomProducts[r + 1]),
                multiplyMod(
                    (c == 0) ? 1 : leftProducts[r][c - 1],
                    (c == m - 1) ? 1 : rightProducts[r][c + 1]));
      }
    }

    return result;
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
