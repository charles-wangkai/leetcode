class Solution {
  public int numberOfSubmatrices(char[][] grid) {
    int row = grid.length;
    int col = grid[0].length;

    int[][] xPrefixCounts = buildPrefixCounts(grid, 'X');
    int[][] yPrefixCounts = buildPrefixCounts(grid, 'Y');

    int result = 0;
    for (int r = 1; r <= row; ++r) {
      for (int c = 1; c <= col; ++c) {
        if (xPrefixCounts[r][c] != 0 && xPrefixCounts[r][c] == yPrefixCounts[r][c]) {
          ++result;
        }
      }
    }

    return result;
  }

  int[][] buildPrefixCounts(char[][] grid, char target) {
    int row = grid.length;
    int col = grid[0].length;

    int[][] result = new int[row + 1][col + 1];
    for (int r = 1; r <= row; ++r) {
      for (int c = 1; c <= col; ++c) {
        result[r][c] =
            result[r - 1][c]
                + result[r][c - 1]
                - result[r - 1][c - 1]
                + ((grid[r - 1][c - 1] == target) ? 1 : 0);
      }
    }

    return result;
  }
}