class Solution {
  public int findLonelyPixel(char[][] picture) {
    int m = picture.length;
    int n = picture[0].length;

    int[] rowBlackCounts = new int[m];
    int[] colBlackCounts = new int[n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (picture[r][c] == 'B') {
          ++rowBlackCounts[r];
          ++colBlackCounts[c];
        }
      }
    }

    int result = 0;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (picture[r][c] == 'B' && rowBlackCounts[r] == 1 && colBlackCounts[c] == 1) {
          ++result;
        }
      }
    }
    return result;
  }
}
