class Solution {
  public int[] findBall(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[] result = new int[n];
    for (int i = 0; i < result.length; ++i) {
      int c = i;
      for (int r = 0; r <= m; ++r) {
        if (r == m) {
          result[i] = c;
        } else if (grid[r][c] == 1) {
          if (c == n - 1 || grid[r][c + 1] == -1) {
            result[i] = -1;

            break;
          }

          ++c;
        } else {
          if (c == 0 || grid[r][c - 1] == 1) {
            result[i] = -1;

            break;
          }

          --c;
        }
      }
    }

    return result;
  }
}
