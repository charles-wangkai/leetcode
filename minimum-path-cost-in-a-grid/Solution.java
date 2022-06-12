import java.util.Arrays;

class Solution {
  public int minPathCost(int[][] grid, int[][] moveCost) {
    int m = grid.length;
    int n = grid[0].length;

    int[] dp = grid[0];
    for (int r = 1; r < m; ++r) {
      int[] nextDp = new int[n];
      Arrays.fill(nextDp, Integer.MAX_VALUE);
      for (int c = 0; c < nextDp.length; ++c) {
        for (int i = 0; i < dp.length; ++i) {
          nextDp[c] = Math.min(nextDp[c], dp[i] + moveCost[grid[r - 1][i]][c] + grid[r][c]);
        }
      }

      dp = nextDp;
    }

    return Arrays.stream(dp).min().getAsInt();
  }
}