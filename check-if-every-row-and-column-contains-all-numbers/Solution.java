import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public boolean checkValid(int[][] matrix) {
    int n = matrix.length;

    return IntStream.range(0, n).allMatch(r -> Arrays.stream(matrix[r]).distinct().count() == n)
        && IntStream.range(0, n)
            .allMatch(c -> IntStream.range(0, n).map(r -> matrix[r][c]).distinct().count() == n);
  }
}