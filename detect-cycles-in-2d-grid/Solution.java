class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public boolean containsCycle(char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (Character.isLowerCase(grid[r][c]) && search(grid, -1, -1, r, c)) {
          return true;
        }
      }
    }

    return false;
  }

  boolean search(char[][] grid, int prevR, int prevC, int r, int c) {
    int row = grid.length;
    int col = grid[0].length;

    grid[r][c] = Character.toUpperCase(grid[r][c]);

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0
          && adjR < row
          && adjC >= 0
          && adjC < col
          && !(adjR == prevR && adjC == prevC)
          && (grid[adjR][adjC] == grid[r][c]
              || (grid[adjR][adjC] == Character.toLowerCase(grid[r][c])
                  && search(grid, r, c, adjR, adjC)))) {
        return true;
      }
    }

    return false;
  }
}