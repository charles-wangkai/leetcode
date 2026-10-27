class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public char[][] finalGrid(char[][] grid) {
    while (true) {
      char[][] next = fall(explode(grid));
      if (isSame(next, grid)) {
        break;
      }

      grid = next;
    }

    return grid;
  }

  char[][] fall(char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    for (int r = m - 1; r >= 1; --r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == '-' && grid[r - 1][c] == '#') {
          grid[r][c] = '#';
          grid[r - 1][c] = '-';
        }
      }
    }

    return grid;
  }

  char[][] explode(char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    boolean[][] destroyed = new boolean[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == '*') {
          boolean fired = false;
          for (int i = 0; i < R_OFFSETS.length; ++i) {
            int adjR = r + R_OFFSETS[i];
            int adjC = c + C_OFFSETS[i];
            if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n && grid[adjR][adjC] == '#') {
              fired = true;
            }
          }

          if (fired) {
            for (int dr = -1; dr <= 1; ++dr) {
              for (int dc = -1; dc <= 1; ++dc) {
                int adjR = r + dr;
                int adjC = c + dc;
                if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n) {
                  destroyed[adjR][adjC] = true;
                }
              }
            }
          }
        }
      }
    }

    char[][] result = new char[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        result[r][c] = (grid[r][c] == '#' && destroyed[r][c]) ? '-' : grid[r][c];
      }
    }

    return result;
  }

  boolean isSame(char[][] grid1, char[][] grid2) {
    int m = grid1.length;
    int n = grid1[0].length;

    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid1[r][c] != grid2[r][c]) {
          return false;
        }
      }
    }

    return true;
  }
}