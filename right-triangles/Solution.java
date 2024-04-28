class Solution {
  public long numberOfRightTriangles(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;

    int[] rowCounts = new int[row];
    int[] colCounts = new int[col];
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        if (grid[r][c] == 1) {
          ++rowCounts[r];
          ++colCounts[c];
        }
      }
    }

    long result = 0;
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        if (grid[r][c] == 1) {
          result += (rowCounts[r] - 1) * (colCounts[c] - 1);
        }
      }
    }

    return result;
  }
}