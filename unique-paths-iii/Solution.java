class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int uniquePathsIII(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;

    int step = -1;
    int startR = -1;
    int startC = -1;
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        if (grid[r][c] != -1) {
          ++step;

          if (grid[r][c] == 1) {
            startR = r;
            startC = c;
          }
        }
      }
    }

    return search(grid, step, startR, startC);
  }

  int search(int[][] grid, int step, int r, int c) {
    if (step == 0) {
      return (grid[r][c] == 2) ? 1 : 0;
    }
    if (grid[r][c] == 2) {
      return 0;
    }

    int row = grid.length;
    int col = grid[0].length;

    int result = 0;
    grid[r][c] = -1;
    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];

      if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && grid[adjR][adjC] != -1) {
        result += search(grid, step - 1, adjR, adjC);
      }
    }
    grid[r][c] = 0;

    return result;
  }
}
