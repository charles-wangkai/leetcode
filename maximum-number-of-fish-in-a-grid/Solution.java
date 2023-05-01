class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int findMaxFish(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int result = 0;
    boolean[][] visited = new boolean[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (!visited[r][c] && grid[r][c] != 0) {
          result = Math.max(result, search(grid, visited, r, c));
        }
      }
    }

    return result;
  }

  int search(int[][] grid, boolean[][] visited, int r, int c) {
    int m = grid.length;
    int n = grid[0].length;

    visited[r][c] = true;

    int result = grid[r][c];
    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0
          && adjR < m
          && adjC >= 0
          && adjC < n
          && !visited[adjR][adjC]
          && grid[adjR][adjC] != 0) {
        result += search(grid, visited, adjR, adjC);
      }
    }

    return result;
  }
}
