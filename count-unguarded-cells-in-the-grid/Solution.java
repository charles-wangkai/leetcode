class Solution {
  public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
    char[][] grid = new char[m][n];
    for (int[] guard : guards) {
      grid[guard[0]][guard[1]] = 'G';
    }
    for (int[] wall : walls) {
      grid[wall[0]][wall[1]] = 'W';
    }

    for (int r = 0; r < m; ++r) {
      boolean seen = false;
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == 'G') {
          seen = true;
        } else if (grid[r][c] == 'W') {
          seen = false;
        } else if (seen) {
          grid[r][c] = 'S';
        }
      }
    }
    for (int r = 0; r < m; ++r) {
      boolean seen = false;
      for (int c = n - 1; c >= 0; --c) {
        if (grid[r][c] == 'G') {
          seen = true;
        } else if (grid[r][c] == 'W') {
          seen = false;
        } else if (seen) {
          grid[r][c] = 'S';
        }
      }
    }
    for (int c = 0; c < n; ++c) {
      boolean seen = false;
      for (int r = 0; r < m; ++r) {
        if (grid[r][c] == 'G') {
          seen = true;
        } else if (grid[r][c] == 'W') {
          seen = false;
        } else if (seen) {
          grid[r][c] = 'S';
        }
      }
    }
    for (int c = 0; c < n; ++c) {
      boolean seen = false;
      for (int r = m - 1; r >= 0; --r) {
        if (grid[r][c] == 'G') {
          seen = true;
        } else if (grid[r][c] == 'W') {
          seen = false;
        } else if (seen) {
          grid[r][c] = 'S';
        }
      }
    }

    int result = 0;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == 0) {
          ++result;
        }
      }
    }

    return result;
  }
}