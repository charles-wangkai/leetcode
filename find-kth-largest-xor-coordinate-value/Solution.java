import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int kthLargestValue(int[][] matrix, int k) {
    int m = matrix.length;
    int n = matrix[0].length;

    int[][] values = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        values[r][c] = matrix[r][c];
        if (r != 0) {
          values[r][c] ^= values[r - 1][c];
        }
        if (c != 0) {
          values[r][c] ^= values[r][c - 1];
        }
        if (r != 0 && c != 0) {
          values[r][c] ^= values[r - 1][c - 1];
        }
      }
    }

    return Arrays.stream(values)
        .flatMapToInt(Arrays::stream)
        .boxed()
        .sorted(Comparator.reverseOrder())
        .skip(k - 1)
        .findFirst()
        .get();
  }
}
