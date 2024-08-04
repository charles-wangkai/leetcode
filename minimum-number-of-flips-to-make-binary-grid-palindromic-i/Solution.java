import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minFlips(int[][] grid) {
    return Math.min(
        computeFlipNum(grid),
        computeFlipNum(
            IntStream.range(0, grid[0].length)
                .mapToObj(c -> IntStream.range(0, grid.length).map(r -> grid[r][c]).toArray())
                .toArray(int[][]::new)));
  }

  int computeFlipNum(int[][] matrix) {
    int col = matrix[0].length;

    return Arrays.stream(matrix)
        .mapToInt(
            line ->
                (int) IntStream.range(0, col / 2).filter(c -> line[c] != line[col - 1 - c]).count())
        .sum();
  }
}