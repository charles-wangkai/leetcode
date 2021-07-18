import java.util.Arrays;

class Solution {
  public long maxPoints(int[][] points) {
    int n = points[0].length;

    long[] sums = new long[n];
    for (int[] row : points) {
      long[] nextSums = new long[n];

      long leftMaxSum = 0;
      for (int i = 0; i < nextSums.length; ++i) {
        leftMaxSum = Math.max(leftMaxSum - 1, sums[i]);
        nextSums[i] = Math.max(nextSums[i], leftMaxSum + row[i]);
      }

      long rightMaxSum = 0;
      for (int i = nextSums.length - 1; i >= 0; --i) {
        rightMaxSum = Math.max(rightMaxSum - 1, sums[i]);
        nextSums[i] = Math.max(nextSums[i], rightMaxSum + row[i]);
      }

      sums = nextSums;
    }

    return Arrays.stream(sums).max().getAsLong();
  }
}
