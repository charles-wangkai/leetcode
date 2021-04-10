class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int longestIncreasingPath(int[][] matrix) {
    int row = matrix.length;
    int col = matrix[0].length;

    int[][] maxLengths = new int[row][col];
    int result = 0;
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        result = Math.max(result, search(matrix, maxLengths, r, c));
      }
    }

    return result;
  }

  int search(int[][] matrix, int[][] maxLengths, int r, int c) {
    int row = matrix.length;
    int col = matrix[0].length;

    if (maxLengths[r][c] == 0) {
      maxLengths[r][c] = 1;
      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = r + R_OFFSETS[i];
        int adjC = c + C_OFFSETS[i];
        if (adjR >= 0
            && adjR < row
            && adjC >= 0
            && adjC < col
            && matrix[adjR][adjC] > matrix[r][c]) {
          maxLengths[r][c] = Math.max(maxLengths[r][c], 1 + search(matrix, maxLengths, adjR, adjC));
        }
      }
    }

    return maxLengths[r][c];
  }
}
