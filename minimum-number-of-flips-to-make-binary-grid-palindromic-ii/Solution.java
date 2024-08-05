class Solution {
  public int minFlips(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int result = 0;
    int count01 = 0;
    int count11 = 0;
    for (int r = 0; r < (m + 1) / 2; ++r) {
      for (int c = 0; c < (n + 1) / 2; ++c) {
        boolean verticalCenter = r == m - 1 - r;
        boolean horizontalCenter = c == n - 1 - c;
        if (verticalCenter && horizontalCenter) {
          if (grid[r][c] == 1) {
            ++result;
          }
        } else if (verticalCenter || horizontalCenter) {
          int value1 = grid[r][c];
          int value2 = verticalCenter ? grid[r][n - 1 - c] : grid[m - 1 - r][c];
          if (value1 != value2) {
            ++count01;
          } else if (value1 == 1) {
            ++count11;
          }
        } else {
          int[] counts = new int[2];
          ++counts[grid[r][c]];
          ++counts[grid[r][n - 1 - c]];
          ++counts[grid[m - 1 - r][c]];
          ++counts[grid[m - 1 - r][n - 1 - c]];

          result += Math.min(counts[0], counts[1]);
        }
      }
    }
    result += count01;
    if (count01 == 0 && count11 % 2 == 1) {
      result += 2;
    }

    return result;
  }
}