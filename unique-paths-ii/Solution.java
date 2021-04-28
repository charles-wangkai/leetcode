class Solution {
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int row = obstacleGrid.length;
    int col = obstacleGrid[0].length;
    int[][] pathNums = new int[row][col];
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        if (obstacleGrid[r][c] == 0) {
          pathNums[r][c] =
              (r == 0 && c == 0)
                  ? 1
                  : ((r == 0 ? 0 : pathNums[r - 1][c]) + (c == 0 ? 0 : pathNums[r][c - 1]));
        }
      }
    }

    return pathNums[row - 1][col - 1];
  }
}
