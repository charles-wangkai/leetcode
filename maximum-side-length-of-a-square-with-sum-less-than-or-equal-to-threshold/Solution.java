class Solution {
  public int maxSideLength(int[][] mat, int threshold) {
    int m = mat.length;
    int n = mat[0].length;

    int[][] prefixSums = new int[m + 1][n + 1];
    for (int r = 1; r <= m; ++r) {
      for (int c = 1; c <= n; ++c) {
        prefixSums[r][c] =
            prefixSums[r - 1][c]
                + prefixSums[r][c - 1]
                - prefixSums[r - 1][c - 1]
                + mat[r - 1][c - 1];
      }
    }

    for (int side = Math.min(m, n); side >= 1; --side) {
      for (int r = 1; r + side - 1 <= m; ++r) {
        for (int c = 1; c + side - 1 <= n; ++c) {
          if (computeRangeSum(prefixSums, r, c, side) <= threshold) {
            return side;
          }
        }
      }
    }

    return 0;
  }

  static int computeRangeSum(int[][] prefixSums, int r, int c, int side) {
    return prefixSums[r + side - 1][c + side - 1]
        - prefixSums[r - 1][c + side - 1]
        - prefixSums[r + side - 1][c - 1]
        + prefixSums[r - 1][c - 1];
  }
}
