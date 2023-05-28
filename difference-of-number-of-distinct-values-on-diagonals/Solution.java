import java.util.HashSet;
import java.util.Set;

class Solution {
  public int[][] differenceOfDistinctValues(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] result = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        result[r][c] =
            Math.abs(computeTopLeftUniqNum(grid, r, c) - computeBottomRightUniqNum(grid, r, c));
      }
    }

    return result;
  }

  int computeTopLeftUniqNum(int[][] grid, int r, int c) {
    Set<Integer> seen = new HashSet<>();
    while (true) {
      --r;
      --c;
      if (r == -1 || c == -1) {
        break;
      }

      seen.add(grid[r][c]);
    }

    return seen.size();
  }

  int computeBottomRightUniqNum(int[][] grid, int r, int c) {
    Set<Integer> seen = new HashSet<>();
    while (true) {
      ++r;
      ++c;
      if (r == grid.length || c == grid[r].length) {
        break;
      }

      seen.add(grid[r][c]);
    }

    return seen.size();
  }
}
