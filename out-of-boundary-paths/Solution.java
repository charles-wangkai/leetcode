class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
    int[][] pathNums = new int[m][n];
    for (int c = 0; c < n; ++c) {
      pathNums[0][c] = addMod(pathNums[0][c], 1);
      pathNums[m - 1][c] = addMod(pathNums[m - 1][c], 1);
    }
    for (int r = 0; r < m; ++r) {
      pathNums[r][0] = addMod(pathNums[r][0], 1);
      pathNums[r][n - 1] = addMod(pathNums[r][n - 1], 1);
    }

    int result = 0;
    for (int i = 0; i < maxMove; ++i) {
      result = addMod(result, pathNums[startRow][startColumn]);

      int[][] nextPathNums = new int[m][n];
      for (int r = 0; r < m; ++r) {
        for (int c = 0; c < n; ++c) {
          for (int j = 0; j < R_OFFSETS.length; ++j) {
            int adjR = r + R_OFFSETS[j];
            int adjC = c + C_OFFSETS[j];
            if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n) {
              nextPathNums[r][c] = addMod(nextPathNums[r][c], pathNums[adjR][adjC]);
            }
          }
        }
      }

      pathNums = nextPathNums;
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
