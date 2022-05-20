class Solution {
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;

    int[][] pathNums = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (obstacleGrid[r][c] == 0) {
          pathNums[r][c] =
              (r == 0 && c == 0)
                  ? 1
                  : (((r == 0) ? 0 : pathNums[r - 1][c]) + ((c == 0) ? 0 : pathNums[r][c - 1]));
        }
      }
    }

    return pathNums[m - 1][n - 1];
  }
}
