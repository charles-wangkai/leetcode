class Solution {
  public int diagonalSum(int[][] mat) {
    int size = mat.length;

    int result = 0;
    for (int r = 0; r < size; ++r) {
      for (int c = 0; c < size; ++c) {
        if (r == c || r + c == size - 1) {
          result += mat[r][c];
        }
      }
    }

    return result;
  }
}
