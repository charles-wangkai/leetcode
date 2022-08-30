class Solution {
  public void rotate(int[][] matrix) {
    int n = matrix.length;

    for (int r = 0; r * 2 < n; ++r) {
      for (int c = r; r + c < n - 1; ++c) {
        int temp = matrix[r][c];
        matrix[r][c] = matrix[n - 1 - c][r];
        matrix[n - 1 - c][r] = matrix[n - 1 - r][n - 1 - c];
        matrix[n - 1 - r][n - 1 - c] = matrix[c][n - 1 - r];
        matrix[c][n - 1 - r] = temp;
      }
    }
  }
}
