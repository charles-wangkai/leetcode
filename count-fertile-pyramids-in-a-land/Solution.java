class Solution {
  public int countPyramids(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] leftCounts = new int[m][n];
    for (int r = 0; r < m; ++r) {
      int leftCount = 0;
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == 1) {
          ++leftCount;
        } else {
          leftCount = 0;
        }

        leftCounts[r][c] = leftCount;
      }
    }

    int[][] rightCounts = new int[m][n];
    for (int r = 0; r < m; ++r) {
      int rightCount = 0;
      for (int c = n - 1; c >= 0; --c) {
        if (grid[r][c] == 1) {
          ++rightCount;
        } else {
          rightCount = 0;
        }

        rightCounts[r][c] = rightCount;
      }
    }

    int result = 0;
    for (int beginR = 0; beginR < m; ++beginR) {
      for (int beginC = 0; beginC < n; ++beginC) {
        if (grid[beginR][beginC] == 1) {
          for (int i = 1;
              beginR + i < m
                  && leftCounts[beginR + i][beginC] >= i + 1
                  && rightCounts[beginR + i][beginC] >= i + 1;
              ++i) {
            ++result;
          }

          for (int i = 1;
              beginR - i >= 0
                  && leftCounts[beginR - i][beginC] >= i + 1
                  && rightCounts[beginR - i][beginC] >= i + 1;
              ++i) {
            ++result;
          }
        }
      }
    }

    return result;
  }
}