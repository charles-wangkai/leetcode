class Solution {
  public int[][] largestLocal(int[][] grid) {
    int n = grid.length;

    int[][] result = new int[n - 2][n - 2];
    for (int r = 0; r < result.length; ++r) {
      for (int c = 0; c < result[0].length; ++c) {
        for (int dr = -1; dr <= 1; ++dr) {
          for (int dc = -1; dc <= 1; ++dc) {
            result[r][c] = Math.max(result[r][c], grid[r + 1 + dr][c + 1 + dc]);
          }
        }
      }
    }

    return result;
  }
}