class Solution {
  static final int MODULUS = 1_000_000_007;

  public int uniquePaths(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    return search(grid, 0, 0, false, new Integer[m][n][2]);
  }

  int search(int[][] grid, int r, int c, boolean fromLeftOrUp, Integer[][][] cache) {
    int m = grid.length;
    int n = grid[0].length;

    if (r == m || c == n) {
      return 0;
    }
    if (r == m - 1 && c == n - 1) {
      return 1;
    }

    int direction = (grid[r][c] == 1) ? (fromLeftOrUp ? 1 : 0) : 0;
    if (cache[r][c][direction] == null) {
      int result;
      if (grid[r][c] == 1) {
        result =
            fromLeftOrUp
                ? search(grid, r + 1, c, false, cache)
                : search(grid, r, c + 1, true, cache);
      } else {
        result = addMod(search(grid, r + 1, c, false, cache), search(grid, r, c + 1, true, cache));
      }

      cache[r][c][direction] = result;
    }

    return cache[r][c][direction];
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
