import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
  public int[][] sortMatrix(int[][] grid) {
    int n = grid.length;

    for (int r = 0; r < n; ++r) {
      sortDiagonal(grid, r, 0, Comparator.reverseOrder());
    }
    for (int c = 1; c < n; ++c) {
      sortDiagonal(grid, 0, c, Comparator.naturalOrder());
    }

    return grid;
  }

  void sortDiagonal(int[][] grid, int beginR, int beginC, Comparator<Integer> comparator) {
    int n = grid.length;

    List<Integer> values = new ArrayList<>();
    for (int r = beginR, c = beginC; r < n && c < n; ++r, ++c) {
      values.add(grid[r][c]);
    }

    Collections.sort(values, comparator);

    for (int i = 0, r = beginR, c = beginC; r < n && c < n; ++i, ++r, ++c) {
      grid[r][c] = values.get(i);
    }
  }
}