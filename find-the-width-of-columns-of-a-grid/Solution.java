import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int[] findColumnWidth(int[][] grid) {
    return IntStream.range(0, grid[0].length)
        .map(
            c ->
                Arrays.stream(grid)
                    .mapToInt(line -> String.valueOf(line[c]).length())
                    .max()
                    .getAsInt())
        .toArray();
  }
}
