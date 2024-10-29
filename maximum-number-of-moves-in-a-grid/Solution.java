class Solution {
  public int maxMoves(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int result = 0;
    boolean[][] reachables = new boolean[m][n];
    for (int c = 0; c < n; ++c) {
      for (int r = 0; r < m; ++r) {
        if (c == 0) {
          reachables[r][c] = true;
        } else {
          for (int dr = -1; dr <= 1; ++dr) {
            int prevR = r + dr;
            if (prevR >= 0
                && prevR < m
                && reachables[prevR][c - 1]
                && grid[r][c] > grid[prevR][c - 1]) {
              reachables[r][c] = true;
              result = c;
            }
          }
        }
      }
    }

    return result;
  }
}
