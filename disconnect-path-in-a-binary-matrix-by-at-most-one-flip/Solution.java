import java.util.stream.IntStream;

class Solution {
  public boolean isPossibleToCutPath(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    boolean[][] reachables = new boolean[m][n];
    int[] counts = new int[m + n - 1];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        reachables[r][c] =
            grid[r][c] == 1
                && ((r == 0 && c == 0)
                    || (r != 0 && reachables[r - 1][c])
                    || (c != 0 && reachables[r][c - 1]));

        if (reachables[r][c]) {
          ++counts[r + c];
        }
      }
    }

    return IntStream.range(1, counts.length - 1).anyMatch(i -> counts[i] <= 1);
  }
}
