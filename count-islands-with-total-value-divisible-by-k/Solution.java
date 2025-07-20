class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int countIslands(int[][] grid, int k) {
    int m = grid.length;
    int n = grid[0].length;

    int result = 0;
    boolean[][] visited = new boolean[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] > 0 && !visited[r][c]) {
          if (search(grid, visited, r, c) % k == 0) {
            ++result;
          }
        }
      }
    }

    return result;
  }

  long search(int[][] grid, boolean[][] visited, int r, int c) {
    int m = grid.length;
    int n = grid[0].length;

    visited[r][c] = true;

    long result = grid[r][c];
    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0
          && adjR < m
          && adjC >= 0
          && adjC < n
          && grid[adjR][adjC] > 0
          && !visited[adjR][adjC]) {
        result += search(grid, visited, adjR, adjC);
      }
    }

    return result;
  }
}