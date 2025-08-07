import java.util.stream.IntStream;

class Solution {
  public int maxCollectedFruits(int[][] fruits) {
    int n = fruits.length;

    return IntStream.range(0, n).map(r -> fruits[r][r]).sum()
        + computeMaxSum(
            IntStream.range(0, n)
                .mapToObj(
                    c ->
                        IntStream.rangeClosed(0, Math.min(c, n - 1 - c))
                            .map(i -> (n - 1 - i == c) ? 0 : fruits[n - 1 - i][c])
                            .toArray())
                .toArray(int[][]::new))
        + computeMaxSum(
            IntStream.range(0, n)
                .mapToObj(
                    r ->
                        IntStream.rangeClosed(0, Math.min(r, n - 1 - r))
                            .map(i -> (r == n - 1 - i) ? 0 : fruits[r][n - 1 - i])
                            .toArray())
                .toArray(int[][]::new));
  }

  int computeMaxSum(int[][] values) {
    for (int i = 1; i < values.length; ++i) {
      for (int j = 0; j < values[i].length; ++j) {
        int prevMax = 0;
        for (int d = -1; d <= 1; ++d) {
          if (j + d >= 0 && j + d < values[i - 1].length) {
            prevMax = Math.max(prevMax, values[i - 1][j + d]);
          }
        }

        values[i][j] += prevMax;
      }
    }

    return values[values.length - 1][0];
  }
}