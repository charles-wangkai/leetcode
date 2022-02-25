import java.util.Arrays;

class Solution {
  public int removeOnes(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int result = Integer.MAX_VALUE;
    for (int mask = 0; mask < 1 << (m * n); ++mask) {
      int[][] g = Arrays.stream(grid).map(r -> r.clone()).toArray(int[][]::new);
      for (int r = 0; r < m; ++r) {
        for (int c = 0; c < n; ++c) {
          if (grid[r][c] == 1 && (mask & (1 << (r * n + c))) != 0) {
            for (int i = 0; i < m; ++i) {
              g[i][c] = 0;
            }
            for (int i = 0; i < n; ++i) {
              g[r][i] = 0;
            }
          }
        }
      }

      if (Arrays.stream(g).allMatch(r -> Arrays.stream(r).allMatch(x -> x == 0))) {
        result = Math.min(result, Integer.bitCount(mask));
      }
    }

    return result;
  }
}