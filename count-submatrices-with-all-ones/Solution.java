class Solution {
  public int numSubmat(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;

    int result = 0;
    for (int beginR = 0; beginR < m; ++beginR) {
      int[] heights = new int[n];
      for (int endR = beginR; endR < m; ++endR) {
        for (int c = 0; c < n; ++c) {
          if (mat[endR][c] == 1) {
            ++heights[c];
          }
        }

        int count = 0;
        for (int c = 0; c <= n; ++c) {
          if (c != n && heights[c] == endR - beginR + 1) {
            ++count;
          } else {
            result += count * (count + 1) / 2;
            count = 0;
          }
        }
      }
    }

    return result;
  }
}
