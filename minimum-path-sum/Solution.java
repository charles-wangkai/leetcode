class Solution {
  public int minPathSum(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] minSums = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        minSums[r][c] =
            grid[r][c]
                + ((r == 0 && c == 0)
                    ? 0
                    : Math.min(
                        (r == 0) ? Integer.MAX_VALUE : minSums[r - 1][c],
                        (c == 0) ? Integer.MAX_VALUE : minSums[r][c - 1]));
      }
    }

    return minSums[m - 1][n - 1];
  }
}
