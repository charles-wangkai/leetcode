import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> luckyNumbers(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    int[] rowMins =
        IntStream.range(0, m)
            .map(r -> IntStream.range(0, n).map(c -> matrix[r][c]).min().getAsInt())
            .toArray();
    int[] colMaxs =
        IntStream.range(0, n)
            .map(c -> IntStream.range(0, m).map(r -> matrix[r][c]).max().getAsInt())
            .toArray();

    return IntStream.range(0, m)
        .flatMap(
            r ->
                IntStream.range(0, n)
                    .filter(c -> matrix[r][c] == rowMins[r] && matrix[r][c] == colMaxs[c])
                    .map(c -> matrix[r][c]))
        .boxed()
        .toList();
  }
}