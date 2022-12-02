// https://leetcode.com/problems/cherry-pickup/solutions/127413/cherry-pickup/
// Approach #3: Dynamic Programming (Bottom Up) [Accepted]

class Solution {
  public int cherryPickup(int[][] grid) {
    int n = grid.length;

    int[][] dp = init(n);
    dp[0][0] = grid[0][0];

    for (int step = 1; step <= 2 * (n - 1); ++step) {
      int[][] nextDp = init(n);
      for (int r1 = 0; r1 < n; ++r1) {
        int c1 = step - r1;
        if (c1 >= 0 && c1 < n) {
          for (int r2 = 0; r2 < n; ++r2) {
            int c2 = step - r2;
            if (c2 >= 0 && c2 < n) {
              int maxPrev =
                  Math.max(
                      Math.max(dp[r1][r2], (r1 == 0) ? -1 : dp[r1 - 1][r2]),
                      Math.max(
                          (r2 == 0) ? -1 : dp[r1][r2 - 1],
                          (r1 == 0 || r2 == 0) ? -1 : dp[r1 - 1][r2 - 1]));
              if (maxPrev != -1 && grid[r1][c1] != -1 && grid[r2][c2] != -1) {
                nextDp[r1][r2] = maxPrev + grid[r1][c1] + ((r1 == r2) ? 0 : grid[r2][c2]);
              }
            }
          }
        }
      }

      dp = nextDp;
    }

    return Math.max(0, dp[n - 1][n - 1]);
  }

  int[][] init(int n) {
    int[][] result = new int[n][n];
    for (int r1 = 0; r1 < n; ++r1) {
      for (int r2 = 0; r2 < n; ++r2) {
        result[r1][r2] = -1;
      }
    }

    return result;
  }
}
