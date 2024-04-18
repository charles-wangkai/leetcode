class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int islandPerimeter(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;

    int result = 0;
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        if (grid[r][c] == 1) {
          for (int i = 0; i < R_OFFSETS.length; ++i) {
            int adjR = r + R_OFFSETS[i];
            int adjC = c + C_OFFSETS[i];
            if (!(adjR >= 0 && adjR < row && adjC >= 0 && adjC < col) || grid[adjR][adjC] == 0) {
              ++result;
            }
          }
        }
      }
    }

    return result;
  }
}
