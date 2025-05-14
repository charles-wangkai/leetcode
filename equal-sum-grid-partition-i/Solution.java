import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public boolean canPartitionGrid(int[][] grid) {
    return canCut(
            Arrays.stream(grid)
                .mapToLong(line -> Arrays.stream(line).asLongStream().sum())
                .toArray())
        || canCut(
            IntStream.range(0, grid[0].length)
                .mapToLong(
                    c -> IntStream.range(0, grid.length).map(r -> grid[r][c]).asLongStream().sum())
                .toArray());
  }

  boolean canCut(long[] values) {
    long total = Arrays.stream(values).sum();
    long sum = 0;
    for (int i = 0; i < values.length - 1; ++i) {
      sum += values[i];
      if (sum * 2 == total) {
        return true;
      }
    }

    return false;
  }
}