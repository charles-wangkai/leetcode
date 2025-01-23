
class Solution {
  public int countServers(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[] rowCounts = new int[m];
    int[] colCounts = new int[n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == 1) {
          ++rowCounts[r];
          ++colCounts[c];
        }
      }
    }

    int result = 0;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == 1 && (rowCounts[r] != 1 || colCounts[c] != 1)) {
          ++result;
        }
      }
    }

    return result;
  }
}
