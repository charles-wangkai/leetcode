class Solution {
  public int cherryPickup(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;

    int[][][] dp = new int[row][col][col];
    for (int r = 0; r < row; ++r) {
      for (int i = 0; i < col; ++i) {
        for (int j = 0; j < col; ++j) {
          dp[r][i][j] = Integer.MIN_VALUE;
        }
      }
    }
    dp[0][0][col - 1] = grid[0][0] + grid[0][col - 1];

    for (int r = 0; r < row - 1; ++r) {
      for (int i = 0; i < col; ++i) {
        for (int j = i; j < col; ++j) {
          if (dp[r][i][j] != Integer.MIN_VALUE) {
            for (int offsetI = -1; offsetI <= 1; ++offsetI) {
              for (int offsetJ = -1; offsetJ <= 1; ++offsetJ) {
                int nextI = i + offsetI;
                int nextJ = j + offsetJ;
                if (nextI >= 0 && nextI < col && nextJ >= 0 && nextJ < col && nextI <= nextJ) {
                  dp[r + 1][nextI][nextJ] =
                      Math.max(
                          dp[r + 1][nextI][nextJ],
                          dp[r][i][j]
                              + grid[r + 1][nextI]
                              + ((nextI == nextJ) ? 0 : grid[r + 1][nextJ]));
                }
              }
            }
          }
        }
      }
    }

    int result = 0;
    for (int i = 0; i < col; ++i) {
      for (int j = i; j < col; ++j) {
        result = Math.max(result, dp[row - 1][i][j]);
      }
    }

    return result;
  }
}
