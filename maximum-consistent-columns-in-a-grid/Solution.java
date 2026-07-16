import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maxConsistentColumns(int[][] grid, int limit) {
    int m = grid.length;
    int n = grid[0].length;

    int[] dp = new int[n];
    for (int c = 0; c < n; ++c) {
      dp[c] = 1;
      for (int prevC = 0; prevC < c; ++prevC) {
        int c_ = c;
        int prevC_ = prevC;
        if (IntStream.range(0, m).allMatch(r -> Math.abs(grid[r][c_] - grid[r][prevC_]) <= limit)) {
          dp[c] = Math.max(dp[c], dp[prevC] + 1);
        }
      }
    }

    return Arrays.stream(dp).max().getAsInt();
  }
}