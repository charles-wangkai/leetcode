import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int deleteGreatestValue(int[][] grid) {
    for (int[] row : grid) {
      Arrays.sort(row);
    }

    return IntStream.range(0, grid[0].length)
        .map(c -> IntStream.range(0, grid.length).map(r -> grid[r][c]).max().getAsInt())
        .sum();
  }
}
