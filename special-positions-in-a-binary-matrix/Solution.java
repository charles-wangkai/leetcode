class Solution {
  public int numSpecial(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;

    int[] rowOneCounts = new int[m];
    int[] colOneCounts = new int[n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (mat[r][c] == 1) {
          ++rowOneCounts[r];
          ++colOneCounts[c];
        }
      }
    }

    int result = 0;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (mat[r][c] == 1 && rowOneCounts[r] == 1 && colOneCounts[c] == 1) {
          ++result;
        }
      }
    }

    return result;
  }
}