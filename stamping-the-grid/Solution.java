class Solution {
  public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] prefixSums = buildPrefixSums(grid);
    int[][] topLefts = new int[m][n];
    for (int r = 0; r + stampHeight - 1 < m; ++r) {
      for (int c = 0; c + stampWidth - 1 < n; ++c) {
        topLefts[r][c] =
            (computeSubMatrixSum(prefixSums, r, c, r + stampHeight - 1, c + stampWidth - 1) == 0)
                ? 1
                : 0;
      }
    }

    int[][] topLeftPrefixSums = buildPrefixSums(topLefts);
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == 0
            && computeSubMatrixSum(topLeftPrefixSums, r - stampHeight + 1, c - stampWidth + 1, r, c)
                == 0) {
          return false;
        }
      }
    }

    return true;
  }

  int[][] buildPrefixSums(int[][] a) {
    int m = a.length;
    int n = a[0].length;

    int[][] result = new int[m + 1][n + 1];
    for (int r = 1; r <= m; ++r) {
      for (int c = 1; c <= n; ++c) {
        result[r][c] = result[r - 1][c] + result[r][c - 1] - result[r - 1][c - 1] + a[r - 1][c - 1];
      }
    }

    return result;
  }

  int computeSubMatrixSum(int[][] prefixSums, int minR, int minC, int maxR, int maxC) {
    int m = prefixSums.length - 1;
    int n = prefixSums[0].length - 1;

    minR = Math.max(0, minR);
    minC = Math.max(0, minC);
    maxR = Math.min(m - 1, maxR);
    maxC = Math.min(n - 1, maxC);

    return prefixSums[maxR + 1][maxC + 1]
        - prefixSums[minR][maxC + 1]
        - prefixSums[maxR + 1][minC]
        + prefixSums[minR][minC];
  }
}