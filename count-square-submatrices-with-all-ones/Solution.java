class Solution {
  public int countSquares(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    int[][] prefixSums = new int[m + 1][n + 1];
    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        prefixSums[i][j] =
            prefixSums[i - 1][j]
                + prefixSums[i][j - 1]
                - prefixSums[i - 1][j - 1]
                + matrix[i - 1][j - 1];
      }
    }

    int result = 0;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        for (int size = 1; r + size <= m && c + size <= n; ++size) {
          if (prefixSums[r + size][c + size]
                  - prefixSums[r][c + size]
                  - prefixSums[r + size][c]
                  + prefixSums[r][c]
              == size * size) {
            ++result;
          }
        }
      }
    }

    return result;
  }
}
