class Solution {
  static final int[] R_OFFSETS = {-1, 1};
  static final int[] C_OFFSETS = {1, -1};

  public int[] findDiagonalOrder(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;

    int[] result = new int[m * n];
    int r = 0;
    int c = 0;
    int direction = 0;
    for (int i = 0; i < result.length; ++i) {
      result[i] = mat[r][c];

      r += R_OFFSETS[direction];
      c += C_OFFSETS[direction];
      if (c == n) {
        r += 2;
        --c;
        direction = 1 - direction;
      } else if (r < 0) {
        r = 0;
        direction = 1 - direction;
      } else if (r == m) {
        --r;
        c += 2;
        direction = 1 - direction;
      } else if (c < 0) {
        c = 0;
        direction = 1 - direction;
      }
    }

    return result;
  }
}
