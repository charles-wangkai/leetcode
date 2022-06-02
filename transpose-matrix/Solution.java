class Solution {
  public int[][] transpose(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int[][] result = new int[n][m];
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < m; ++c) {
        result[r][c] = matrix[c][r];
      }
    }

    return result;
  }
}
