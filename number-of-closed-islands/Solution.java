class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int closedIsland(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;

    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        if ((r == 0 || r == row - 1 || c == 0 || c == col - 1) && grid[r][c] == 0) {
          fill(grid, r, c);
        }
      }
    }

    int result = 0;
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        if (grid[r][c] == 0) {
          fill(grid, r, c);
          ++result;
        }
      }
    }

    return result;
  }

  void fill(int[][] grid, int r, int c) {
    int row = grid.length;
    int col = grid[0].length;

    grid[r][c] = 1;

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && grid[adjR][adjC] == 0) {
        fill(grid, adjR, adjC);
      }
    }
  }
}
