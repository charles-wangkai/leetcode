class Solution {
  public int largestOverlap(int[][] img1, int[][] img2) {
    int n = img1.length;

    int result = 0;
    for (int rOffset = -(n - 1); rOffset <= n - 1; ++rOffset) {
      for (int cOffset = -(n - 1); cOffset <= n - 1; ++cOffset) {
        result = Math.max(result, overlap(translate(img1, rOffset, cOffset), img2));
      }
    }

    return result;
  }

  int[][] translate(int[][] image, int rOffset, int cOffset) {
    int n = image.length;

    int[][] result = new int[n][n];
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        int originalR = r + rOffset;
        int originalC = c + cOffset;
        if (originalR >= 0 && originalR < n && originalC >= 0 && originalC < n) {
          result[r][c] = image[originalR][originalC];
        }
      }
    }

    return result;
  }

  int overlap(int[][] image1, int[][] image2) {
    int n = image1.length;

    int result = 0;
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        if (image1[r][c] == 1 && image2[r][c] == 1) {
          ++result;
        }
      }
    }

    return result;
  }
}
