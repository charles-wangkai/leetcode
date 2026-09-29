import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
  public boolean hasValidPath(char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    @SuppressWarnings("unchecked")
    Set<Integer>[][] dp = new Set[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        dp[r][c] = new HashSet<>();
      }
    }
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (r == 0 && c == 0) {
          if (grid[0][0] == '(') {
            dp[0][0].add(1);
          }
        } else {
          List<Integer> prevs = new ArrayList<>();
          if (r != 0) {
            prevs.addAll(dp[r - 1][c]);
          }
          if (c != 0) {
            prevs.addAll(dp[r][c - 1]);
          }

          for (int prev : prevs) {
            int current = prev + ((grid[r][c] == '(') ? 1 : -1);
            if (current != -1) {
              dp[r][c].add(current);
            }
          }
        }
      }
    }

    return dp[m - 1][n - 1].contains(0);
  }
}