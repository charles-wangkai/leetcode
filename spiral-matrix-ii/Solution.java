class Solution {
  static final int[] R_OFFSETS = {0, 1, 0, -1};
  static final int[] C_OFFSETS = {1, 0, -1, 0};

  public int[][] generateMatrix(int n) {
    int[][] result = new int[n][n];
    int r = 0;
    int c = -1;
    int direction = 0;
    for (int i = 0; i < n * n; ++i) {
      r += R_OFFSETS[direction];
      c += C_OFFSETS[direction];
      if (!(r >= 0 && r < n && c >= 0 && c < n && result[r][c] == 0)) {
        r -= R_OFFSETS[direction];
        c -= C_OFFSETS[direction];

        direction = (direction + 1) % R_OFFSETS.length;

        r += R_OFFSETS[direction];
        c += C_OFFSETS[direction];
      }

      result[r][c] = i + 1;
    }

    return result;
  }
}
