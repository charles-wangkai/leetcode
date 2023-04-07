import java.util.stream.IntStream;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int numEnclaves(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    boolean[][] visited = new boolean[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if ((r == 0 || r == m - 1 || c == 0 || c == n - 1) && grid[r][c] == 1 && !visited[r][c]) {
          search(grid, visited, r, c);
        }
      }
    }

    return IntStream.range(0, m)
        .map(
            r -> (int) IntStream.range(0, n).filter(c -> grid[r][c] == 1 && !visited[r][c]).count())
        .sum();
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
