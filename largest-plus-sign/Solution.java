import java.util.Arrays;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int orderOfLargestPlusSign(int n, int[][] mines) {
    char[][] grid = new char[n][n];
    for (int r = 0; r < n; ++r) {
      Arrays.fill(grid[r], '1');
    }
    for (int[] mine : mines) {
      grid[mine[0]][mine[1]] = '0';
    }

    int maxOrder = 0;
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        maxOrder = Math.max(maxOrder, computeOrder(grid, r, c));
      }
    }

    return maxOrder;
  }

  int computeOrder(char[][] grid, int r, int c) {
    for (int order = 1; ; ++order) {
      if (!isAllOne(grid, r, c, order)) {
        return order - 1;
      }
    }
  }

  boolean isAllOne(char[][] grid, int r, int c, int order) {
    int n = grid.length;
    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int nextR = r + R_OFFSETS[i] * (order - 1);
      int nextC = c + C_OFFSETS[i] * (order - 1);
      if (!(nextR >= 0 && nextR < n && nextC >= 0 && nextC < n && grid[nextR][nextC] == '1')) {
        return false;
      }
    }

    return true;
  }
}
