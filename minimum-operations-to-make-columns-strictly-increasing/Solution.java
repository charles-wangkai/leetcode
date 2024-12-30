import java.util.stream.IntStream;

class Solution {
  public int minimumOperations(int[][] grid) {
    return IntStream.range(0, grid[0].length)
        .map(
            c ->
                computeOperationNum(IntStream.range(0, grid.length).map(r -> grid[r][c]).toArray()))
        .sum();
  }

  int computeOperationNum(int[] values) {
    int result = 0;
    for (int i = 1; i < values.length; ++i) {
      int delta = Math.max(0, values[i - 1] + 1 - values[i]);
      values[i] += delta;
      result += delta;
    }

    return result;
  }
}