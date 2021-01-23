class Solution {
  public int[][] diagonalSort(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;

    boolean changed;
    do {
      changed = false;

      for (int r = 0; r < m - 1; ++r) {
        for (int c = 0; c < n - 1; ++c) {
          if (mat[r][c] > mat[r + 1][c + 1]) {
            int temp = mat[r][c];
            mat[r][c] = mat[r + 1][c + 1];
            mat[r + 1][c + 1] = temp;

            changed = true;
          }
        }
      }
    } while (changed);

    return mat;
  }
}
