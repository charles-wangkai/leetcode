import java.util.stream.IntStream;

class Solution {
  public void setZeroes(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    boolean zeroInFirstRow = IntStream.range(0, n).anyMatch(c -> matrix[0][c] == 0);
    boolean zeroInFirstCol = IntStream.range(0, m).anyMatch(r -> matrix[r][0] == 0);

    for (int r = 1; r < m; ++r) {
      int r_ = r;
      if (IntStream.range(0, n).anyMatch(c -> matrix[r_][c] == 0)) {
        matrix[r][0] = 0;
      }
    }

    for (int c = 1; c < n; ++c) {
      int c_ = c;
      if (IntStream.range(0, m).anyMatch(r -> matrix[r][c_] == 0)) {
        matrix[0][c] = 0;
      }
    }

    for (int r = 1; r < m; ++r) {
      for (int c = 1; c < n; ++c) {
        if (matrix[r][0] == 0 || matrix[0][c] == 0) {
          matrix[r][c] = 0;
        }
      }
    }

    if (zeroInFirstRow) {
      for (int c = 0; c < n; ++c) {
        matrix[0][c] = 0;
      }
    }
    if (zeroInFirstCol) {
      for (int r = 0; r < m; ++r) {
        matrix[r][0] = 0;
      }
    }
  }
}
