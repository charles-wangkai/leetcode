import java.util.Arrays;

class Solution {
  public int minFallingPathSum(int[][] grid) {
    int n = grid.length;

    int[] dp = grid[0];
    for (int r = 1; r < n; ++r) {
      int[] nextDp = new int[n];
      Arrays.fill(nextDp, Integer.MAX_VALUE);

      for (int c = 0; c < nextDp.length; ++c) {
        for (int prevC = 0; prevC < dp.length; ++prevC) {
          if (prevC != c) {
            nextDp[c] = Math.min(nextDp[c], dp[prevC] + grid[r][c]);
          }
        }
      }

      dp = nextDp;
    }

    return Arrays.stream(dp).min().getAsInt();
  }
}
