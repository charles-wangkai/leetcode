class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int numIslands(char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int result = 0;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == '1') {
          flood(grid, r, c);
          ++result;
        }
      }
    }

    return result;
  }

  void flood(char[][] grid, int r, int c) {
    int m = grid.length;
    int n = grid[0].length;

    grid[r][c] = '0';

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n && grid[adjR][adjC] == '1') {
        flood(grid, adjR, adjC);
      }
    }
  }
}
