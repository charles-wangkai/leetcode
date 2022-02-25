// https://www.geeksforgeeks.org/program-check-three-points-collinear

import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minimumLines(int[][] points) {
    int[] dp = new int[1 << points.length];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int mask = 1; mask < dp.length; ++mask) {
      int mask_ = mask;
      for (int prevMask = 0; prevMask < mask; ++prevMask) {
        int prevMask_ = prevMask;
        if ((mask | prevMask) == mask) {
          int[] indices =
              IntStream.range(0, points.length)
                  .filter(i -> ((mask_ - prevMask_) & (1 << i)) != 0)
                  .toArray();
          if (IntStream.range(0, indices.length - 2)
              .allMatch(i -> isOneLine(points, indices[i], indices[i + 1], indices[i + 2]))) {
            dp[mask] = Math.min(dp[mask], dp[prevMask] + 1);
          }
        }
      }
    }

    return dp[dp.length - 1];
  }

  boolean isOneLine(int[][] points, int index1, int index2, int index3) {
    return points[index1][0] * points[index2][1]
            - points[index1][1] * points[index2][0]
            + points[index2][0] * points[index3][1]
            - points[index2][1] * points[index3][0]
            + points[index3][0] * points[index1][1]
            - points[index3][1] * points[index1][0]
        == 0;
  }
}