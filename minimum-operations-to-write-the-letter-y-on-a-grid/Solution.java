class Solution {
  public int minimumOperationsToWriteY(int[][] grid) {
    int result = Integer.MAX_VALUE;
    for (int foreValue = 0; foreValue <= 2; ++foreValue) {
      for (int backValue = 0; backValue <= 2; ++backValue) {
        if (backValue != foreValue) {
          result = Math.min(result, computeOperationNum(grid, foreValue, backValue));
        }
      }
    }

    return result;
  }

  int computeOperationNum(int[][] grid, int foreValue, int backValue) {
    int n = grid.length;

    int result = 0;
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] != (isFore(n, r, c) ? foreValue : backValue)) {
          ++result;
        }
      }
    }

    return result;
  }

  boolean isFore(int n, int r, int c) {
    return (r >= n / 2) ? (c == n / 2) : (r == c || r + c == n - 1);
  }
}