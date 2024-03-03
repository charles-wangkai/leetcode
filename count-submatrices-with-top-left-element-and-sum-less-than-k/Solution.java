class Solution {
  public int countSubmatrices(int[][] grid, int k) {
    int m = grid.length;
    int n = grid[0].length;

    int result = 0;
    int[][] prefixSums = new int[m + 1][n + 1];
    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        prefixSums[i][j] =
            prefixSums[i - 1][j]
                + prefixSums[i][j - 1]
                - prefixSums[i - 1][j - 1]
                + grid[i - 1][j - 1];

        if (prefixSums[i][j] <= k) {
          ++result;
        }
      }
    }

    return result;
  }
}