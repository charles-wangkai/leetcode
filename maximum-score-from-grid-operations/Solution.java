// https://leetcode.com/problems/maximum-score-from-grid-operations/solutions/5507349/python-dp-solution-with-o-n-3-time-complexity-o-n-space-complexity/

import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long maximumScore(int[][] grid) {
    int n = grid.length;

    if (n == 1) {
      return 0;
    }

    long[] prevWithScores = new long[n + 1];
    long[] prevWithoutScores = new long[n + 1];
    for (int c = 1; c < n; ++c) {
      long[] currWithScores = new long[n + 1];
      long[] currWithoutScores = new long[n + 1];
      for (int prevHeight = 0; prevHeight <= n; ++prevHeight) {
        long prevColScore = 0;

        int c_ = c;
        long currColScore =
            IntStream.range(0, prevHeight).map(r -> grid[r][c_]).asLongStream().sum();

        for (int currHeight = 0; currHeight <= n; ++currHeight) {
          if (currHeight > prevHeight) {
            prevColScore += grid[currHeight - 1][c - 1];
          } else if (currHeight != 0) {
            currColScore -= grid[currHeight - 1][c];
          }

          currWithScores[currHeight] =
              Math.max(
                  currWithScores[currHeight],
                  Math.max(
                      currColScore + prevWithScores[prevHeight],
                      currColScore + prevColScore + prevWithoutScores[prevHeight]));
          currWithoutScores[currHeight] =
              Math.max(
                  currWithoutScores[currHeight],
                  Math.max(
                      prevWithScores[prevHeight], prevColScore + prevWithoutScores[prevHeight]));
        }
      }

      prevWithScores = currWithScores;
      prevWithoutScores = currWithoutScores;
    }

    return Arrays.stream(prevWithScores).max().getAsLong();
  }
}