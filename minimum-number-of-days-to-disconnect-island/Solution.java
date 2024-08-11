class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int minDays(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    if (!isConnected(grid)) {
      return 0;
    }

    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == 1) {
          grid[r][c] = 0;

          if (!isConnected(grid)) {
            return 1;
          }

          grid[r][c] = 1;
        }
      }
    }

    return 2;
  }

  boolean isConnected(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    boolean[][] visited = new boolean[m][n];
    int groupCount = 0;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == 1 && !visited[r][c]) {
          search(grid, visited, r, c);
          ++groupCount;
        }
      }
    }

    return groupCount == 1;
  }

  void search(int[][] grid, boolean[][] visited, int r, int c) {
    int m = grid.length;
    int n = grid[0].length;

    visited[r][c] = true;

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0
          && adjR < m
          && adjC >= 0
          && adjC < n
          && grid[adjR][adjC] == 1
          && !visited[adjR][adjC]) {
        search(grid, visited, adjR, adjC);
      }
    }
  }
}