import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int matrixScore(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    for (int r = 0; r < m; ++r) {
      if (grid[r][0] == 0) {
        flipRow(grid, r);
      }
    }

    for (int c = 0; c < n; ++c) {
      int c_ = c;
      int oneCount = IntStream.range(0, m).map(r -> grid[r][c_]).sum();

      if (oneCount < m - oneCount) {
        flipCol(grid, c);
      }
    }

    return Arrays.stream(grid).mapToInt(this::toValue).sum();
  }

  int toValue(int[] line) {
    int result = 0;
    for (int b : line) {
      result = result * 2 + b;
    }

    return result;
  }

  void flipRow(int[][] grid, int r) {
    for (int c = 0; c < grid[r].length; ++c) {
      grid[r][c] = 1 - grid[r][c];
    }
  }

  void flipCol(int[][] grid, int c) {
    for (int r = 0; r < grid.length; ++r) {
      grid[r][c] = 1 - grid[r][c];
    }
  }
}
