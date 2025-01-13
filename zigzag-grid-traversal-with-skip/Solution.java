import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> zigzagTraversal(int[][] grid) {
    int[] values =
        IntStream.range(0, grid.length)
            .flatMap(
                r ->
                    IntStream.range(0, grid[r].length)
                        .map(c -> (r % 2 == 0) ? grid[r][c] : grid[r][grid[r].length - 1 - c]))
            .toArray();

    return IntStream.range(0, values.length)
        .filter(i -> i % 2 == 0)
        .map(i -> values[i])
        .boxed()
        .toList();
  }
}