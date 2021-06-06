class Solution {
  public boolean findRotation(int[][] mat, int[][] target) {
    for (int i = 0; i < 4; ++i) {
      if (isEqual(mat, target)) {
        return true;
      }

      mat = rotate(mat);
    }

    return false;
  }

  boolean isEqual(int[][] mat, int[][] target) {
    int n = mat.length;

    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        if (mat[r][c] != target[r][c]) {
          return false;
        }
      }
    }

    return true;
  }

  int[][] rotate(int[][] mat) {
    int n = mat.length;

    int[][] rotated = new int[n][n];
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        rotated[r][c] = mat[c][n - 1 - r];
      }
    }

    return rotated;
  }
}
