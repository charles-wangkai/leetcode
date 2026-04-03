import java.util.HashSet;
import java.util.Set;

class Solution {
  public int minCost(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    @SuppressWarnings("unchecked")
    Set<Integer>[][] dp = new Set[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        dp[r][c] = new HashSet<>();
      }
    }
    dp[0][0].add(grid[0][0]);

    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        for (int xor : dp[r][c]) {
          if (r != m - 1) {
            dp[r + 1][c].add(xor ^ grid[r + 1][c]);
          }
          if (c != n - 1) {
            dp[r][c + 1].add(xor ^ grid[r][c + 1]);
          }
        }
      }
    }

    return dp[m - 1][n - 1].stream().mapToInt(Integer::intValue).min().getAsInt();
  }
}