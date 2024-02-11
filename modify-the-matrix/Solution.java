import java.util.stream.IntStream;

class Solution {
  public int[][] modifiedMatrix(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    int[][] result = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        int c_ = c;
        result[r][c] =
            (matrix[r][c] == -1)
                ? IntStream.range(0, m).map(i -> matrix[i][c_]).max().getAsInt()
                : matrix[r][c];
      }
    }

    return result;
  }
}