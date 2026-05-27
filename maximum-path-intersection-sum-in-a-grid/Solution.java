import java.util.stream.IntStream;

class Solution {
  public int maxScore(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int result = Integer.MIN_VALUE;
    for (int r = 1; r <= m - 2; ++r) {
      for (int c = 1; c <= n - 2; ++c) {
        result = Math.max(result, grid[r][c]);
      }
    }
    for (int r = 0; r < m; ++r) {
      result = Math.max(result, computeMaxRangeSum(grid[r]));
    }
    for (int c = 0; c < n; ++c) {
      int c_ = c;
      result =
          Math.max(
              result, computeMaxRangeSum(IntStream.range(0, m).map(r -> grid[r][c_]).toArray()));
    }

    return result;
  }

  int computeMaxRangeSum(int[] values) {
    int[] prefixSums = new int[values.length + 1];
    for (int i = 1; i < prefixSums.length; ++i) {
      prefixSums[i] = prefixSums[i - 1] + values[i - 1];
    }

    int result = Integer.MIN_VALUE;
    for (int i = 0; i < values.length; ++i) {
      for (int j = i + 1; j < values.length; ++j) {
        int rangeSum = prefixSums[j + 1] - prefixSums[i];

        result = Math.max(result, rangeSum);
      }
    }

    return result;
  }
}
