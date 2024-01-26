class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
    int[][] dp = new int[m][n];
    dp[startRow][startColumn] = 1;

    int result = 0;
    for (int i = 0; i < maxMove; ++i) {
      int[][] nextDp = new int[m][n];
      for (int r = 0; r < m; ++r) {
        for (int c = 0; c < n; ++c) {
          for (int j = 0; j < R_OFFSETS.length; ++j) {
            int adjR = r + R_OFFSETS[j];
            int adjC = c + C_OFFSETS[j];
            if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n) {
              nextDp[adjR][adjC] = addMod(nextDp[adjR][adjC], dp[r][c]);
            } else {
              result = addMod(result, dp[r][c]);
            }
          }
        }
      }

      dp = nextDp;
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
