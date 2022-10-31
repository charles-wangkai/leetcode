import java.util.stream.IntStream;

class Solution {
  public boolean isToeplitzMatrix(int[][] matrix) {
    return IntStream.range(1, matrix.length)
        .allMatch(
            r ->
                IntStream.range(1, matrix[r].length)
                    .allMatch(c -> matrix[r][c] == matrix[r - 1][c - 1]));
  }
}
