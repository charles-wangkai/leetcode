import java.util.stream.IntStream;

class Solution {
  public int[] findMissingAndRepeatedValues(int[][] grid) {
    int n = grid.length;

    int[] counts = new int[n * n + 1];
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        ++counts[grid[r][c]];
      }
    }

    return new int[] {
      IntStream.range(1, counts.length).filter(i -> counts[i] == 2).findAny().getAsInt(),
      IntStream.range(1, counts.length).filter(i -> counts[i] == 0).findAny().getAsInt()
    };
  }
}