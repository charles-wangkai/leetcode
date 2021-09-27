import java.util.stream.IntStream;

class Solution {
  public long gridGame(int[][] grid) {
    int n = grid[0].length;

    long[] leftSums = new long[n];
    for (int i = 1; i < leftSums.length; ++i) {
      leftSums[i] = leftSums[i - 1] + grid[1][i - 1];
    }

    long[] rightSums = new long[n];
    for (int i = rightSums.length - 2; i >= 0; --i) {
      rightSums[i] = rightSums[i + 1] + grid[0][i + 1];
    }

    return IntStream.range(0, n)
        .mapToLong(i -> Math.max(leftSums[i], rightSums[i]))
        .min()
        .getAsLong();
  }
}
