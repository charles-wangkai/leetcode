class Solution {
  static final int MAX_HEIGHT = 1_000_000;
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int minimumEffortPath(int[][] heights) {
    int result = -1;
    int lower = 0;
    int upper = MAX_HEIGHT;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(heights, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[][] heights, int effort) {
    int row = heights.length;
    int col = heights[0].length;

    return search(heights, effort, new boolean[row][col], 0, 0);
  }

  boolean search(int[][] heights, int effort, boolean[][] visited, int r, int c) {
    int row = heights.length;
    int col = heights[0].length;

    visited[r][c] = true;

    if (r == row - 1 && c == col - 1) {
      return true;
    }

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0
          && adjR < row
          && adjC >= 0
          && adjC < col
          && Math.abs(heights[r][c] - heights[adjR][adjC]) <= effort
          && !visited[adjR][adjC]
          && search(heights, effort, visited, adjR, adjC)) {
        return true;
      }
    }

    return false;
  }
}
