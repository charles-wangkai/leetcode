class Solution {
  public int[][] seePeople(int[][] heights) {
    int m = heights.length;
    int n = heights[0].length;

    int[][] result = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        result[r][c] = computeSeeNum(heights, r, c, 0, 1) + computeSeeNum(heights, r, c, 1, 0);
      }
    }

    return result;
  }

  int computeSeeNum(int[][] heights, int beginR, int beginC, int offsetR, int offsetC) {
    int m = heights.length;
    int n = heights[0].length;

    int result = 0;
    int maxHeight = Integer.MIN_VALUE;
    for (int r = beginR + offsetR, c = beginC + offsetC;
        r < m && c < n;
        r += offsetR, c += offsetC) {
      if (maxHeight < Math.min(heights[beginR][beginC], heights[r][c])) {
        ++result;
      }
      maxHeight = Math.max(maxHeight, heights[r][c]);
    }

    return result;
  }
}
