class Solution {
  public boolean satisfiesConditions(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if ((r != m - 1 && grid[r][c] != grid[r + 1][c])
            || (c != n - 1 && grid[r][c] == grid[r][c + 1])) {
          return false;
        }
      }
    }

    return true;
  }
}