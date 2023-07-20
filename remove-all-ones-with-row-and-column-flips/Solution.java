import java.util.Arrays;

class Solution {
  public boolean removeOnes(int[][] grid) {
    if (check(Arrays.stream(grid).map(int[]::clone).toArray(int[][]::new))) {
      return true;
    }

    flip(grid, 0, 0, 0, 1);

    return check(grid);
  }

  boolean check(int[][] grid) {
    for (int c = 0; c < grid[0].length; ++c) {
      if (grid[0][c] == 1) {
        flip(grid, 0, c, 1, 0);
      }
    }

    return Arrays.stream(grid).allMatch(r -> Arrays.stream(r).distinct().count() == 1);
  }

  void flip(int[][] grid, int beginR, int beginC, int offsetR, int offsetC) {
    int m = grid.length;
    int n = grid[0].length;

    int r = beginR;
    int c = beginC;
    while (r >= 0 && r < m && c >= 0 && c < n) {
      grid[r][c] = 1 - grid[r][c];

      r += offsetR;
      c += offsetC;
    }
  }
}
