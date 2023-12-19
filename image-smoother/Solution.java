class Solution {
  public int[][] imageSmoother(int[][] img) {
    int m = img.length;
    int n = img[0].length;

    int[][] result = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        result[r][c] = smooth(img, r, c);
      }
    }

    return result;
  }

  int smooth(int[][] img, int r, int c) {
    int m = img.length;
    int n = img[0].length;

    int sum = 0;
    int count = 0;
    for (int dr = -1; dr <= 1; ++dr) {
      for (int dc = -1; dc <= 1; ++dc) {
        int adjR = r + dr;
        int adjC = c + dc;
        if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n) {
          sum += img[adjR][adjC];
          ++count;
        }
      }
    }

    return sum / count;
  }
}
