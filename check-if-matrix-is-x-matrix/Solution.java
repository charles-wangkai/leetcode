class Solution {
  public boolean checkXMatrix(int[][] grid) {
    int n = grid.length;

    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        if ((r + c != n - 1 && r != c) != (grid[r][c] == 0)) {
          return false;
        }
      }
    }

    return true;
  }
}