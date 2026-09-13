class Solution {
  public int[][] cyclicShift(int n, int[][] grid, int[] rowShift, int[] colShift) {
    return shiftUp(shiftLeft(grid, rowShift), colShift);
  }

  int[][] shiftLeft(int[][] grid, int[] rowShift) {
    int n = grid.length;

    int[][] result = new int[n][n];
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        result[r][c] = grid[r][(c + rowShift[r]) % n];
      }
    }

    return result;
  }

  int[][] shiftUp(int[][] grid, int[] colShift) {
    int n = grid.length;

    int[][] result = new int[n][n];
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        result[r][c] = grid[(r + colShift[c]) % n][c];
      }
    }

    return result;
  }
}