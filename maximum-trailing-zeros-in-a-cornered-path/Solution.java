class Solution {
  public int maxTrailingZeros(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[][][] twoSums = {
      buildUpSums(grid, 2), buildRightSums(grid, 2), buildDownSums(grid, 2), buildLeftSums(grid, 2)
    };
    int[][][] fiveSums = {
      buildUpSums(grid, 5), buildRightSums(grid, 5), buildDownSums(grid, 5), buildLeftSums(grid, 5)
    };

    int result = 0;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        int twoExponent = computeExponent(grid[r][c], 2);
        int fiveExponent = computeExponent(grid[r][c], 5);

        for (int i = 0; i < twoSums.length; ++i) {
          result =
              Math.max(
                  result,
                  Math.min(
                      twoSums[i][r][c] + twoSums[(i + 1) % twoSums.length][r][c] - twoExponent,
                      fiveSums[i][r][c]
                          + fiveSums[(i + 1) % fiveSums.length][r][c]
                          - fiveExponent));
        }
      }
    }

    return result;
  }

  int computeExponent(int value, int divisor) {
    int result = 0;
    while (value % divisor == 0) {
      ++result;
      value /= divisor;
    }

    return result;
  }

  int[][] buildUpSums(int[][] grid, int divisor) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] result = new int[m][n];
    for (int c = 0; c < n; ++c) {
      for (int r = 0; r < m; ++r) {
        result[r][c] = ((r == 0) ? 0 : result[r - 1][c]) + computeExponent(grid[r][c], divisor);
      }
    }

    return result;
  }

  int[][] buildRightSums(int[][] grid, int divisor) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] result = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = n - 1; c >= 0; --c) {
        result[r][c] = ((c == n - 1) ? 0 : result[r][c + 1]) + computeExponent(grid[r][c], divisor);
      }
    }

    return result;
  }

  int[][] buildDownSums(int[][] grid, int divisor) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] result = new int[m][n];
    for (int c = 0; c < n; ++c) {
      for (int r = m - 1; r >= 0; --r) {
        result[r][c] = ((r == m - 1) ? 0 : result[r + 1][c]) + computeExponent(grid[r][c], divisor);
      }
    }

    return result;
  }

  int[][] buildLeftSums(int[][] grid, int divisor) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] result = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        result[r][c] = ((c == 0) ? 0 : result[r][c - 1]) + computeExponent(grid[r][c], divisor);
      }
    }

    return result;
  }
}