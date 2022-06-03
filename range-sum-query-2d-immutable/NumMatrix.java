class NumMatrix {
  int[][] sums;

  public NumMatrix(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    sums = new int[m + 1][n + 1];
    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        sums[i][j] = sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1] + matrix[i - 1][j - 1];
      }
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    return sums[row2 + 1][col2 + 1]
        - sums[row1][col2 + 1]
        - sums[row2 + 1][col1]
        + sums[row1][col1];
  }
}

// Your NumMatrix object will be instantiated and called as such:
// NumMatrix obj = new NumMatrix(matrix);
// int param_1 = obj.sumRegion(row1,col1,row2,col2);
